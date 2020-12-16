#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <pthread.h>

//From PL14 RCOMP
#include <openssl/crypto.h>
#include <openssl/ssl.h>
#include <openssl/err.h>
#include <openssl/conf.h>
#include <openssl/x509.h>

#define BUF_SIZE 30
#define SERVER_PORT "9910"
#define SERVER_PORT_CONFIG "9909"
#define PATH_SIZE 50
#define SLEEP 20

#define PROTOCOL_VERSION 0
#define UDP_PROTOCOL_MAXSIZE 512
#define TCP_PROTOCOL_MAXSIZE  65535
#define PROTOCOL_MAXSIZE 65535

#define HELLO_CODE 0
#define MSG_CODE 1
#define CONFIG_CODE 2
#define RESET_CODE 3
#define END_CODE 4

#define ACK 150
#define NACK 151

//From PL14 RCOMP
#define SERVER_SSL_CERT_FILE "server.pem"
#define SERVER_SSL_KEY_FILE "server.key"
#define AUTH_CLIENTS_SSL_CERTS_FILE "authentic-clients.pem"

//This struct is shared between multiple threads and keeps the necessary information available for all of them to access
struct SimulationStruct
{
    int status;
	int reset;
    char *server_ip;
    char *cadency;
    char *id_machine;
    char *path;
    char *clientName;
    char config[TCP_PROTOCOL_MAXSIZE];
};

void *receiveConfigRequest(void *arg)
{
    struct SimulationStruct *info = (struct SimulationStruct *)arg;

    char configFromServer[TCP_PROTOCOL_MAXSIZE];
    char auxiliarMessageToRespond[TCP_PROTOCOL_MAXSIZE];

    unsigned short mID = (unsigned short)strtoul(info->id_machine, NULL, 0); //machine ID
    int byte_1 = mID / 100;
    //De acordo com o protocolo de comunicação o segundo byte tem de ser multiplicado por 256
    int byte_2 = (mID % 100) * 256;

    struct sockaddr_storage from;
    int err, newSock, sock;
    unsigned int adl;
    struct addrinfo req, *list;
    char cliIPtext[BUF_SIZE], cliPortText[BUF_SIZE];

    bzero((char *)&req, sizeof(req));
    // requesting a IPv6 local address will allow both IPv4 and IPv6 clients to use it
    req.ai_family = AF_INET6;
    req.ai_socktype = SOCK_STREAM;
    req.ai_flags = AI_PASSIVE; // local address

    err = getaddrinfo(NULL, SERVER_PORT_CONFIG, &req, &list);

    if (err)
    {
        printf("Failed to get local address, error: %s\n", gai_strerror(err));
        exit(1);
    }

    sock = socket(list->ai_family, list->ai_socktype, list->ai_protocol);
    if (sock == -1)
    {
        perror("Failed to open socket");
        freeaddrinfo(list);
        exit(1);
    }

    if (bind(sock, (struct sockaddr *)list->ai_addr, list->ai_addrlen) == -1)
    {
        perror("Bind failed");
        close(sock);
        freeaddrinfo(list);
        exit(1);
    }

    freeaddrinfo(list);

    listen(sock, SOMAXCONN);

    const SSL_METHOD *method;
    SSL_CTX *ctx;
    method = SSLv23_server_method();
    ctx = SSL_CTX_new(method);
    // Load the server's certificate and key
    SSL_CTX_use_certificate_file(ctx, SERVER_SSL_CERT_FILE, SSL_FILETYPE_PEM);
    SSL_CTX_use_PrivateKey_file(ctx, SERVER_SSL_KEY_FILE, SSL_FILETYPE_PEM);
    if (!SSL_CTX_check_private_key(ctx))
    {
        puts("Error loading server's certificate/key");
        close(sock);
        exit(1);
    }
    // THE CLIENTS' CERTIFICATES ARE TRUSTED
    SSL_CTX_load_verify_locations(ctx, AUTH_CLIENTS_SSL_CERTS_FILE, NULL);
    // Restrict TLS version and cypher suite
    SSL_CTX_set_min_proto_version(ctx, TLS1_2_VERSION);
    SSL_CTX_set_cipher_list(ctx, "HIGH:!aNULL:!kRSA:!PSK:!SRP:!MD5:!RC4");
    // Clients must provide a trusted certificate, the handshake will fail otherwise
    SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER | SSL_VERIFY_FAIL_IF_NO_PEER_CERT, NULL);

    adl = sizeof(from);
    for (;;)
    {
        newSock = accept(sock, (struct sockaddr *)&from, &adl);
        if (!fork())
        {
            close(sock);
            getnameinfo((struct sockaddr *)&from, adl, cliIPtext, BUF_SIZE, cliPortText, BUF_SIZE, NI_NUMERICHOST | NI_NUMERICSERV);
            printf("THE SCM IS TRYING TO SET UP A CONFIG REQUEST...\n");

            SSL *sslConn = SSL_new(ctx);
            SSL_set_fd(sslConn, newSock);
            if (SSL_accept(sslConn) != 1)
            {
                puts("TLS handshake error: client not authorized");
                SSL_free(sslConn);
                close(newSock);
                exit(1);
            }
            printf("TLS version: %s\nCypher suite: %s\n",
                   SSL_get_cipher_version(sslConn), SSL_get_cipher(sslConn));
            X509 *cert = SSL_get_peer_certificate(sslConn);
            X509_free(cert);
            X509_NAME_oneline(X509_get_subject_name(cert), cliIPtext, BUF_SIZE);
            printf("Client's certificate subject: %s\n", cliIPtext);

            SSL_read(sslConn, &configFromServer, TCP_PROTOCOL_MAXSIZE);
            if (configFromServer[1] == (char)CONFIG_CODE)
            {
                if (configFromServer[2] == *info->id_machine) //if the server is reachig the correct machine
                {
                    auxiliarMessageToRespond[0] = (char)PROTOCOL_VERSION;
                    auxiliarMessageToRespond[1] = (char)ACK;
                    auxiliarMessageToRespond[4] = (byte_1 + byte_2) & 0xff;
                    auxiliarMessageToRespond[5] = ((byte_1 + byte_2) >> 8) & 0xff;
                    SSL_write(sslConn, &auxiliarMessageToRespond, TCP_PROTOCOL_MAXSIZE);
                }
                else //the ID that the server is trying to reach is not this one
                {
                    auxiliarMessageToRespond[0] = (char)PROTOCOL_VERSION;
                    auxiliarMessageToRespond[1] = (char)NACK;
                    auxiliarMessageToRespond[4] = (byte_1 + byte_2) & 0xff;
                    auxiliarMessageToRespond[5] = ((byte_1 + byte_2) >> 8) & 0xff;
                    SSL_write(sslConn, &auxiliarMessageToRespond, TCP_PROTOCOL_MAXSIZE);
                    printf("THE SERVER REACHED AN INCORRECT MACHINE...\n");
                    close(newSock);
                }
            }

            //----------------------------------------------------------------------------------------------------------------

            SSL_read(sslConn, &configFromServer, TCP_PROTOCOL_MAXSIZE); //After responding to the server the server sends the configuration file

            int i;
            for (i = 6; i < TCP_PROTOCOL_MAXSIZE; i++)
            {
                info->config[i - 6] = configFromServer[i]; //copies the config in the server to the config of the machine in the simulation info
            }

            printf("RECEIVED AND SET THE MACHINE CONFIGURATION...\n");

            //----------------------------------------------------------------------------------------------------------------

            close(newSock);
            printf("CLOSED THE CONNECTION WITH THE SCM...\n");
            exit(0);
        }
    }
    close(sock);
}

void *startFileReader(void *arg)
{

    //Pode-se notar comentários em EN e PT no código, não existe uma razão para a incoerência, apenas por uma questão de rapides os comentários surgem em linguagens diferentes
    char keyLine[BUF_SIZE];
    struct SimulationStruct *info = (struct SimulationStruct *)arg;

    //Creating the client and server messages for response and sending the info
    char client_message[TCP_PROTOCOL_MAXSIZE];
    char server_response[TCP_PROTOCOL_MAXSIZE];

    //setting the path for the file with the messages
    char *path;
    path = info->path;

    //getting the simulation info from the parameters
    char *machine_id = info->id_machine;
    char *cadency = info->cadency;
    int cadencyNum = atoi(cadency);

    int err, sock, i;
    struct addrinfo req, *list;

    //apaga a data a partir de onde está a apontar
    bzero((char *)&req, sizeof(req));

    req.ai_family = AF_UNSPEC;
    req.ai_socktype = SOCK_STREAM;

    err = getaddrinfo(info->server_ip, SERVER_PORT, &req, &list);
    if (err)
    {
        printf("FAILED TO GET THE SERVER ADDRESS... %s\n", gai_strerror(err));
        exit(1);
    }

    sock = socket(list->ai_family, list->ai_socktype, list->ai_protocol);
    if (sock == -1)
    {
        perror("FAILED TO OPEN THE SOCKET...");
        freeaddrinfo(list);
        exit(1);
    }

    if (connect(sock, (struct sockaddr *)list->ai_addr, list->ai_addrlen) == -1)
    {
        perror("FAILED TO CONNECT TO THE SERVER...");
        freeaddrinfo(list);
        close(sock);
        exit(1);
    }

    //FROM PL14 RCOMP
    const SSL_METHOD *method = SSLv23_client_method();
    SSL_CTX *ctx = SSL_CTX_new(method);

    strcpy(keyLine, info->clientName);
    strcat(keyLine, ".pem");
    SSL_CTX_use_certificate_file(ctx, keyLine, SSL_FILETYPE_PEM);
    strcpy(keyLine, info->clientName);
    strcat(keyLine, ".key");
    SSL_CTX_use_PrivateKey_file(ctx, keyLine, SSL_FILETYPE_PEM);
    if (!SSL_CTX_check_private_key(ctx))
    {
        puts("Error loading client's certificate/key");
        close(sock);
        exit(1);
    }

    SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER, NULL);
    // THE SERVER'S CERTIFICATE IS TRUSTED
    SSL_CTX_load_verify_locations(ctx, SERVER_SSL_CERT_FILE, NULL);
    // Restrict TLS version and cypher suites
    SSL_CTX_set_min_proto_version(ctx, TLS1_2_VERSION);
    SSL_CTX_set_cipher_list(ctx, "HIGH:!aNULL:!kRSA:!PSK:!SRP:!MD5:!RC4");
    SSL *sslConn = SSL_new(ctx);
    SSL_set_fd(sslConn, sock);
    if (SSL_connect(sslConn) != 1)
    {
        puts("TLS handshake error");
        SSL_free(sslConn);
        close(sock);
        exit(1);
    }
    printf("TLS version: %s\nCypher suite: %s\n",
           SSL_get_cipher_version(sslConn), SSL_get_cipher(sslConn));
    if (SSL_get_verify_result(sslConn) != X509_V_OK)
    {
        puts("Sorry: invalid server certificate");
        SSL_free(sslConn);
        close(sock);
        exit(1);
    }

    X509 *cert = SSL_get_peer_certificate(sslConn);
    X509_free(cert);
    if (cert == NULL)
    {
        puts("Sorry: no certificate provided by the server");
        SSL_free(sslConn);
        close(sock);
        exit(1);
    }

    //-----------------------------------------------------------------------------------------------------------------

    unsigned short mID = (unsigned short)strtoul(machine_id, NULL, 0); //machine ID
    int byte_1 = mID / 100;
    //De acordo com o protocolo de comunicação o segundo byte tem de ser multiplicado por 256
    int byte_2 = (mID % 100) * 256;

    client_message[0] = (char)PROTOCOL_VERSION;
    client_message[1] = (char)HELLO_CODE;
    client_message[2] = (byte_1 + byte_2) & 0xFF;
    client_message[3] = ((byte_1 + byte_2) >> 8) & 0xFF;
    //This has no raw data
    client_message[4] = 0; //no data on HELLO MESSAGE
    client_message[5] = 0; //no data on HELLO MESSAGE

    SSL_write(sslConn, &client_message, TCP_PROTOCOL_MAXSIZE);
    printf("SENT HELLO MESSAGE...\n");

    printf("READING SERVER'S RESPONSE...\n");
    SSL_read(sslConn, &server_response, TCP_PROTOCOL_MAXSIZE);

    if (server_response[1] == (char)NACK)
    {
        printf("THIS MACHINE DOES NOT EXIST IN THE SERVER...\nCLOSING CONECTION...\n");
        close(sock);
        pthread_exit(NULL);
    }

    FILE *fp = fopen(path, "r"); //opening the file with the messages in read mode
    ssize_t line;
    char *buffer;
    size_t length;

    if (!fp)
    {
        printf("CANNOT OPEN THE FILE...\n");
        exit(-1); //exit with error code -1 for debuggind purposes
    }

    //sets the machine status to 1 == working
    info->status = 1;

    while ((line = getline(&buffer, &length, fp)) != -1) //if the library can get the line from the file, or else -1 is returned
    {
        unsigned short data_length = (unsigned short)line;
        int byte_message_1 = data_length / 100;
        int byte_message_2 = (data_length % 100) * 256;

        client_message[0] = (char)PROTOCOL_VERSION;
        client_message[1] = (char)MSG_CODE;
        client_message[4] = (byte_message_1 + byte_message_2) & 0xff;
        client_message[5] = ((byte_message_1 + byte_message_2) >> 8) & 0xff;

        for (i = 6; i < line; i++)
        {
            client_message[i] = buffer[i - 6];
        }

        //Secure writing of the message from the file
        SSL_write(sslConn, &client_message, 6 + data_length);

        //Secure reading the response from the server
        SSL_read(sslConn, &server_response, 6 + data_length);
        if (server_response[1] == (char)NACK)
        {
            printf("THE SERVER IS BUSY AND CAN'T PROCESS THE MESSAGES...\n");
            close(sock);
            pthread_exit(NULL);
        }

        sleep(cadencyNum);
    }

    //sets the machine status to 0 == idle
    info->status = 0;

    printf("ALL MESSAGES OF THE FILE WERE SENT...\n");

    client_message[0] = (char)PROTOCOL_VERSION;
    client_message[1] = (char)END_CODE; //warns the server that the file reached the end

    //This approach was taken because we can differentiate between a null message (in case the file has one)
    //Or the end of the file processing
    client_message[4] = 0;
    client_message[5] = 0;

    //closes the connection to the server
    close(sock);
    SSL_free(sslConn);
    pthread_exit(NULL);
}

void clearString(char* array) {
	int i;
	for(i = 0; i < BUF_SIZE; i++) {
			array[i] = 0;
		}
}

void *monitorState(void *arg)
{

    struct SimulationStruct *info = (struct SimulationStruct *)arg;
    char messageACK[] = "Received message, all good";

    struct sockaddr_storage client;
    int err, sock, res;
    unsigned int serverAddrLen;
    char linha[BUF_SIZE];
    char IPtext[BUF_SIZE], portText[BUF_SIZE];
    char response[BUF_SIZE];
    struct addrinfo req, *list;
    bzero((char *)&req, sizeof(req));
	int isActive = 0;

    // requesting a IPv6 local address will allow both IPv4 and IPv6 clients to use it
    req.ai_family = AF_INET6;
    req.ai_socktype = SOCK_DGRAM;
    req.ai_flags = AI_PASSIVE; // local address

    err = getaddrinfo(NULL, portText, &req, &list);
    if (err)
    {
        printf("Failed to get local address, error: %s\n", gai_strerror(err));
        exit(1);
    }
    // after the getaddrinfo, the socket is created and binded to the local address
    sock = socket(list->ai_family, list->ai_socktype, list->ai_protocol);
    if (sock == -1)
    {
        perror("Failed to open socket\n");
        freeaddrinfo(list);
        exit(1);
    }
    if (bind(sock, (struct sockaddr *)list->ai_addr, list->ai_addrlen) == -1)
    {
        perror("Bind failed\n");
        close(sock);
        freeaddrinfo(list);
        exit(1);
    }

    freeaddrinfo(list);

    serverAddrLen = sizeof(client);

    while (1)
    {
        // waits for a client to send a request
        res = recvfrom(sock, (char *)linha, BUF_SIZE, 0, (struct sockaddr *)&client, &serverAddrLen); //line received is stored in "linha"
        if (!getnameinfo((struct sockaddr *)&client, serverAddrLen,
                         IPtext, BUF_SIZE, portText, BUF_SIZE, NI_NUMERICHOST | NI_NUMERICSERV))
        {
            printf("Request from node %s, port number %s\n", IPtext, portText);
        }
        else
        {
            puts("Got request, but failed to get client address\n");
        }
        linha[res] = 0; //ends the string
        int code = (unsigned char)linha[1];

        switch (code)
        {
			case HELLO_CODE:
				printf("Received Hello message\n");
				clearString(response);
				if (info->status == 1)
				{
					sprintf(response, "%d %s %s", ACK, info->id_machine, messageACK);
					sendto(sock, response, BUF_SIZE, 0, (struct sockaddr *)&client, serverAddrLen);
					isActive = 1;
					break;
				}
				else
				{
					sprintf(response, "%d %s", NACK, info->id_machine);
					sendto(sock, response, BUF_SIZE, 0, (struct sockaddr *)&client, serverAddrLen);
					printf("Sent response NACK\n");
					break;
				}
			 case RESET_CODE:
				printf("Received reset message\n");
				clearString(response);
				if(isActive == 0) {
						printf("Hello message not received");
						sprintf(response, "%d %s %s", NACK, info->id_machine, "Hello request missing");
						sendto(sock, response, BUF_SIZE, 0, (struct sockaddr *)&client, serverAddrLen);
						break;
					}
				else {
					sleep(SLEEP);
					info->reset = HELLO_CODE;
					//missing: cliente manda pedido hello e altera reset para ACK ou NACK correspondente
					sleep(SLEEP);
					if(info->reset == ACK) {				
						clearString(response);
						printf("ACK response – the request has been accepted and executed.");
						sprintf(response, "%d %s %s", ACK, info->id_machine, "ACK response");
						sendto(sock, response, BUF_SIZE, 0, (struct sockaddr *)&client, serverAddrLen);
					}
					
					if(info->reset == NACK) {
						clearString(response);
						printf("NACK response – the request has been refused and ignored.");
						sprintf(response, "%d %s %s", NACK, info->id_machine, "NACK response");
						sendto(sock, response, BUF_SIZE, 0, (struct sockaddr *)&client, serverAddrLen);	
					}
				}
				break;
			default:
				break;
        }
		
        if (info->status == 0)
        {
            break;
        }
    }

    close(sock);
    pthread_exit(NULL);
    exit(0);
}

int main(int argc, char **argv) // ./main IP ID_MAQUINA CADENCIA PATH_TO_FILE CLIENTNAME
{

    int numThreads = 3;
    pthread_t threads[numThreads];
    int i;

    if (argc != 5)
    {
        printf("INCORRECT NUMBER OF ARGUMENTS...\nPLEASE RUN THE SIMULATOR USING: ./main 'SERVER IP' 'MACHINE ID' 'CADENCY' 'PATH TO THE FILE WITH THE MESSAGES' 'PUBLIC KEY' 'CLIENT NAME'\n");
        exit(0);
    }

    //set the information to run the simulation
    //this struct is shared between threads and hosts the needed information

    struct SimulationStruct *info = (struct SimulationStruct *)malloc(sizeof(struct SimulationStruct));
    info->status = 0;
	info->reset = 0;			//Variable to help with the reset request
    info->server_ip = argv[1];  //IP
    info->id_machine = argv[2]; //The ID of the machine
    info->cadency = argv[3];    //The sleep cadency between messages
    info->path = argv[4];       //The path to the file with the messages
    info->clientName = argv[6]; //The name for this client

    pthread_create(&threads[0], NULL, startFileReader, info);      //Thread to read and emit the messages to the server with TLS/SSL
    pthread_create(&threads[1], NULL, monitorState, info);         //Thread to continuously receive monitor requests from the SMM in UDP
    pthread_create(&threads[2], NULL, receiveConfigRequest, info); //Thread to continously receive configuration requests from the SCM in TCP with TLS/SSL

    //Este ciclo for vai garantir que o programa não termina antes de todas as threads pararem, esperando por todas
    for (i = 0; i < numThreads; i++)
    {
        pthread_join(threads[i], NULL);
    }

    return 0;
}
package eapli.base.app.scm.application;

import eapli.base.machinemanagement.domain.Maquina;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class SendConfigRequestService {
    static InetAddress IPdestino;
    static SSLSocket sock;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    static final int SERVER_PORT = 30604;
    static final String KEYSTORE_PASS = "forgotten";

    public void SendTcpCliTo(Maquina machine) throws Exception {


        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", "certificates.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore", "certificates.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);


        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        System.out.println(machine.getIp());

        try {
            IPdestino = InetAddress.getByName(machine.getIp());
        } catch (UnknownHostException ex) {
            System.out.println("\nConnection Error");
            System.exit(1);
        }

        System.out.println(IPdestino);

        try {
            sock = (SSLSocket) sf.createSocket(IPdestino, SERVER_PORT);
        } catch (IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }

        System.out.println("\nConnected to: " + IPdestino + ":" + SERVER_PORT);

        sock.startHandshake();


        sOut = new DataOutputStream(sock.getOutputStream());
        sIn = new DataInputStream(sock.getInputStream());

        short id_Maquina = Short.parseShort((String.valueOf(machine.getUniqueID())));

        //CONFIG fILE
        byte[] configFile = machine.getConfig();

        System.out.println(Arrays.toString(configFile));

        // CONFIG MESSAGE
        byte[] config = new byte[4];
        config[0] = 0;
        config[1] = (byte) 2;
        config[2] = (byte) (id_Maquina & 0xff);
        config[3] = (byte) ((id_Maquina >> 8) & 0xff);

        //CONFIG FILE LENGTH
        byte[] fileLength = ByteBuffer.allocate(2).putShort(Short.reverseBytes((short) configFile.length)).array();

        //MACHINE ID
        byte[] id_MaquinaArray = new byte[2];
        id_MaquinaArray[0] = config[2];
        id_MaquinaArray[1] = config[3];


        System.out.println("\n\n\nMACHINE: " + machine);

        sOut.write(config[1]);
        System.out.println("\nMESSAGE ID: " + config[1]);
        sOut.write(id_Maquina);
        System.out.println("\nMACHINE ID: " + Arrays.toString(id_MaquinaArray));
        sOut.write(fileLength);
        System.out.println("\nCONFIG FILE LENGTH: " + Arrays.toString(fileLength));
        sOut.write(configFile);
        System.out.println("\nCONFIG FILE DATA " + Arrays.toString(configFile));

        System.out.print("\nSending Configuration Request to: " + IPdestino);
        try {
            System.out.println("\nWaiting for reponse...");
            sIn.readByte();
            int code = sIn.readByte() + 256;
            System.out.println("\nMessage Back code:" + code);
            if (code == 150) {
                System.out.println("\nACK CODE by: " + IPdestino + "\nConfiguration Request Sucessful");
            } else {
                System.out.println("\nNACK CODE by: " + IPdestino + "\nConfiguration Request Unsucessful");
            }
        } catch (SocketTimeoutException ex) {
            System.out.println("No reply");
            return;
        }
        sIn.close();
        sOut.close();
        sock.close();
    }
}

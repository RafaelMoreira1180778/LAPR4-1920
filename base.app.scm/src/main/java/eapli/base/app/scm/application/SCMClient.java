package eapli.base.app.scm.application;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;

public class SCMClient {

    static InetAddress serverIP;
    static Socket sock;

    public void sendRequest(String ipAddress, String filepath) {


        if (ipAddress.isEmpty()) {
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }

        try {
            serverIP = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + ipAddress);
            System.exit(1);
        }

        try {
            sock = new Socket(serverIP, 999);
        } catch (IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }

        try {
            DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());

            byte[] ts = new byte[0];

            try {
                ts = Files.readAllBytes(new File(filepath).toPath());
                sOut.write(ts);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package eapli.base.app.client.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.domain.Maquina;
import eapli.base.productionlinemanagement.repositories.LinhaProducaoRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Iterator;

public class MonitorMachinesService {
    static InetAddress IPdestino;
    private static int TIMEOUT = 30;
    private static LinhaProducaoRepository plr = PersistenceContext.repositories().linhaProducaoManagement();

    public boolean SendUdpCliTo() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket sock = new DatagramSocket();
        sock.setSoTimeout(1000 * TIMEOUT); // set the socket timeout
        //HelloMessage hello = new HelloMessage();
        Iterator it = plr.findAll().iterator();
        byte[] hello = new byte[2];
        hello[0] = 0;
        hello[1] = 0;
        while (it.hasNext()) {
            Maquina m = (Maquina) it.next();
            System.out.println("\n\n\nIP: " + m.getIp());

            try {
                IPdestino = InetAddress.getByName(m.getIp());
            } catch (UnknownHostException ex) {
                System.out.println("ConnectionError");
                System.exit(1);
            }
            DatagramPacket udpPacket = new DatagramPacket(hello, hello.length, IPdestino, 30604);
            // DatagramPacket udpPacket= new DatagramPacket("a".getBytes(), "a".length(),IPdestino,30604);
            System.out.print("\nSending Hello Request to: " + IPdestino);
            sock.send(udpPacket);
            try {
                System.out.println("\nWaiting for reponse...");
                sock.receive(udpPacket);
                int code = udpPacket.getData()[1] + 256;

                System.out.println("\nMSG CODE::" + code);

                //String machineId = new String(udpPacket.getData(), 2, 2);
                if (code == 150) {
                        /*
                        int size = convertByteToInt(Arrays.copyOfRange(udpPacket.getData(), 4, 5));
                        String state = new String(udpPacket.getData(), 6, size);
                        System.out.println("Received reply. STATE: " + state);
                        */
                    System.out.println("\nACK CODE by: " + IPdestino);
                    m.activateState();
                    plr.save(plr.findByID(m.getLinhaProducao()));
                    System.out.println("\n\nState Saved!");
                } else {
                    System.out.println("\nNACK CODE by: " + IPdestino);
                    m.deactivateState();
                    plr.save(plr.findByID(m.getLinhaProducao()));
                }
            } catch (SocketTimeoutException ex) {
                System.out.println("No reply");
                m.deactivateState();
                plr.save(plr.findByID(m.getLinhaProducao()));
                return false;
                //estado inativo
            }
        }
        return true;
    }

}
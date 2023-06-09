package hidratarse.ejemplos.UDP.UDPBroadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BroadcastTest {
    private static final int RANDOM_PORT = 4444;

    public static void main(String[] args)
            throws Exception {
        InetAddress addr = getBroadcastAddrs().get(0);
        System.err.println(addr);
        new Thread(new BroadcastServer(RANDOM_PORT)).start();
        try (DatagramSocket dsock = new DatagramSocket()) {
            byte[] send = "Hello World".getBytes("UTF-8");
            DatagramPacket data = new DatagramPacket(send, send.length, addr, RANDOM_PORT);
            dsock.send(data);
        }
    }

    public static List<InetAddress> getBroadcastAddrs() throws SocketException {
        Set<InetAddress> set = new LinkedHashSet<>();
        Enumeration<NetworkInterface> nicList = NetworkInterface.getNetworkInterfaces();
        for (; nicList.hasMoreElements();) {
            NetworkInterface nic = nicList.nextElement();
            if (nic.isUp() && !nic.isLoopback()) {
                for (InterfaceAddress ia : nic.getInterfaceAddresses()){
                    set.add(ia.getBroadcast());
                }
            }
        }
        return Arrays.asList(set.toArray(new InetAddress[0]));

    }
}

class BroadcastServer implements Runnable {
    private final int port;

    public BroadcastServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            try (DatagramSocket dsock = new DatagramSocket(port)) {
                DatagramPacket data = new DatagramPacket(new byte[2048], 2048);
                dsock.receive(data);
                System.out.println(new String(data.getData(), "UTF-8"));
            }
        } catch (SocketException ex) {
            Logger.getLogger(BroadcastServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BroadcastServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
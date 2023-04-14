package ServidorUdp.MULTICASTPROFE;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
public class MulticastSend {
    public static void main(String[] args) {
        try (MulticastSocket ms = new MulticastSocket()){   
            InetAddress group=InetAddress.getByName("230.0.0.0");
            ms.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

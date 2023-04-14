package ServidorUdp.BROADCAST;

import java.net.*;
import java.io.*;
  
public class GreetingServer {
    static DatagramSocket socket;
 
    public static void main(String args[]) throws Exception {
      socket = new DatagramSocket(9876);
  
      byte[] buf = new byte[256];
      
      //Receive incoming datagram packets
      while (true) {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String msg = new String(packet.getData());
        System.out.println("Broadcasted message: " + msg);
        
        //Send response datagram packet 
        InetAddress recipient = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket("Hi!".getBytes(), 
                                    "Hi!".length(), 
                                    recipient, port);
        socket.send(packet);
        System.out.println("Sent the response");
      }
    }
}

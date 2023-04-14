package ServidorUdp.BROADCAST;

import java.net.*;
import java.io.*;
  
public class GreetingClient {
    static DatagramSocket socket;
 
    public static void main(String args[]) throws IOException {
     socket = new DatagramSocket();
 
      //send broadcast 
      byte[] buf = "Hello, server.".getBytes();
      InetAddress address = InetAddress.getByName("192.168.1.255");
      DatagramPacket packet = new DatagramPacket(buf, buf.length, 
                                    address, 9876);
      socket.send(packet);
 
      //Waiting for response
      byte[] bufReceive = new byte[256];
      packet=new DatagramPacket(bufReceive, bufReceive.length);
      socket.receive(packet);
      String msg = new String(packet.getData());
      System.out.println("Message received: " + msg);
    }
}


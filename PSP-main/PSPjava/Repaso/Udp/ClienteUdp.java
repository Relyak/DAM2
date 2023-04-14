package Repaso.Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class ClienteUdp {
    public static void main(String[] args) {
        String mensaje="";
        String ip="127.0.0.1";
        final int MAX_LENGTH=65535;
        int puerto=8888;
        Scanner sc=new Scanner(System.in);
        try(DatagramSocket ds=new DatagramSocket();) {
            do{
            byte[]buffer=new byte[MAX_LENGTH];
            String msg=sc.nextLine();
            buffer=msg.getBytes();
           
           
            InetAddress direccion;
           
            direccion=InetAddress.getByName(ip);
            DatagramPacket d=new DatagramPacket(buffer, buffer.length,direccion,puerto);

            ds.send(d);
            }while(!mensaje.equalsIgnoreCase("adios"));
            
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
    
}

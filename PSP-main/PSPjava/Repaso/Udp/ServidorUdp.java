package Repaso.Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUdp {
    static int MAX_LENGTH=65535;
    static int puerto=8888;
    public static void main(String[] args) {
        try(DatagramSocket ds= new DatagramSocket(puerto)){
            byte []buffer=new byte[MAX_LENGTH];
            String mensaje="";
            DatagramPacket p= new DatagramPacket(buffer, MAX_LENGTH);
            while(true){
                ds.receive(p);
                mensaje=new String(p.getData(),0,p.getLength());
                if(mensaje.equalsIgnoreCase("hola")){
                    System.out.println("que tal");
                }else{
                    System.out.println("no has enviado hola");
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

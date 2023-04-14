package ServidorUdp.BROADCAST;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class EjemploCliente {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            byte buffer[] = "Hola jamon".getBytes(); 
            String ip="192.168.1.136";
            int puerto = 8888;
                DatagramPacket p = new DatagramPacket(
                    buffer,
                    buffer.length,
                    InetAddress.getByName(ip),
                    puerto
                    );
                
                ds.send(p);
            } catch (UnknownHostException e) {             
                e.printStackTrace();
            } catch (SocketException e) {      
            e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
    }
}
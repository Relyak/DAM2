package ServidorUdp.P2P;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class EjemploServidor {
    //tamanio maximo que recibe el buffer
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        int puerto = 8888;//puerto
        try(DatagramSocket ds = new DatagramSocket(puerto);) {
            byte[] buffer = new byte[MAX_LENGTH];//tamanio maximo del buffer

            DatagramPacket p = new DatagramPacket(//en el packet recibe buffer de tamanio maximo
                    buffer,
                    MAX_LENGTH);
            do {//bucle infinito para recibir varios mensajes
                ds.receive(p);
                System.out.print("Cliente manda: "+new String(p.getData(), 0, p.getLength()));
            } while (1>0);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

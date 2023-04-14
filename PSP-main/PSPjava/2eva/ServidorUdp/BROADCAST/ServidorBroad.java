package ServidorUdp.BROADCAST;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ServidorBroad {

    public static final int MAX_LENGTH = 63535;
    public static final String MENSALE_SALIR = "bye";
    public static final String IP_BROADCAST = "255.255.255.255";

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //int port = Integer.parseInt(args[0]);
        int port= 8888;
        //Buffer utilizados para enviar / recibir mensajes
        byte bufferServer[] = new byte[MAX_LENGTH];
        byte bufferCliente[] = new byte[MAX_LENGTH];

        try (DatagramSocket ds = new DatagramSocket()) {

            //Activando Broadcast
            ds.setBroadcast(true);

            String msgCliente="",mensajeServer="";
            while (!msgCliente.equalsIgnoreCase(MENSALE_SALIR) || mensajeServer.equalsIgnoreCase(MENSALE_SALIR)) {

                //Envia mensajes al cliente
                mensajeServer = "Servidor envia: "+sc.nextLine()+"\n";
                bufferCliente = mensajeServer.getBytes();
                

                //Creamos el nuevo paquete y los enviamos 
                DatagramPacket p = new DatagramPacket(bufferCliente, bufferCliente.length, InetAddress.getByName(IP_BROADCAST),port);
                ds.send(p);

                //Recibe el mensaje del cliente y lo muestra en pantalla
                p = new DatagramPacket(bufferServer,bufferServer.length);
                ds.receive(p);
                msgCliente = new String (p.getData(),0,p.getLength()).replace("\n","");
                System.out.println("Cliente envia: "+msgCliente);

            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
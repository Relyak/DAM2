package Repaso.primosUdp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PrimoUdpCli {

    static Scanner sc = new Scanner(System.in);
    static String FIN = "fin";
    static String direccionIP = "192.168.1.136";
    static String localhost = "127.0.0.1";
    static int MAX_LENGTH = 65535;
    public static void main(String[] args) {
        String numero = "";

        try (DatagramSocket ds = new DatagramSocket();) {
            do {
                
                System.out.println("Dime el numero, fin para salir");
                numero = sc.nextLine();
                byte[] buffer = numero.getBytes();
                InetAddress addressConectar;
                // para ir a ip addressConectar = InetAddress.getByName(direccionIP);
                // para conectarse a localhost
                addressConectar = InetAddress.getByName(localhost);
                DatagramPacket p = new DatagramPacket(buffer, buffer.length, addressConectar, 8881);
                ds.send(p);
                if(!numero.equalsIgnoreCase(FIN)){
                byte[] bufferRecibido = new byte[MAX_LENGTH];
                DatagramPacket pRecibido = new DatagramPacket(bufferRecibido, MAX_LENGTH);
                ds.receive(pRecibido);
                String mensajeRecibido = new String(pRecibido.getData(), 0, pRecibido.getLength());
                System.out.println(mensajeRecibido);
                }else{
                    System.out.println("Fin de la conexión");
                    break;
                }

            } while (!numero.equalsIgnoreCase(FIN));

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
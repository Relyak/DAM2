package Repaso.primosUdp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class PrimoUdpSer {

    public static int MAX_LENGTH = 65535;

    public static void main(String[] args) {

        try (DatagramSocket ds = new DatagramSocket(8881);) {
            String mensajeRecibido = "";
            int numeroParseado = 0;
            String mensajeEnviar;
            byte buffer[] = new byte[MAX_LENGTH];
            System.out.println("Devuelvo los números primos que lleguen");
            DatagramPacket p = new DatagramPacket(
                    buffer,
                    MAX_LENGTH);
            while (true) {
                ds.receive(p);
                mensajeRecibido = new String(p.getData(), 0, p.getLength());
                numeroParseado = Integer.parseInt(mensajeRecibido);
                if (EsPrimo(numeroParseado)) {
                    mensajeEnviar = "El numero: " + numeroParseado + " es primo";
                    System.out.println("El numero: " + numeroParseado + " es primo");

                } else {
                    mensajeEnviar = "El numero: " + numeroParseado + " no es primo";
                    System.out.println("El numero: " + numeroParseado + " no es primo");
                }
                byte[] bufferEnviar = mensajeEnviar.getBytes();
                InetAddress address = p.getAddress();
                int port = p.getPort();
                DatagramPacket pEnviar = new DatagramPacket(bufferEnviar, bufferEnviar.length, address, port);
                ds.send(pEnviar);

            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Fin de la conexión");
        }

    }

    private static boolean EsPrimo(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}

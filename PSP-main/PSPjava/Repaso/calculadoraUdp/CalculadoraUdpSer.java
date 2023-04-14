package Repaso.calculadoraUdp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class CalculadoraUdpSer {
    private static final int MAX_LENGTH = 1024; // Tamaño máximo del mensaje a recibir
    static String FIN = "fin";
    static int puerto = 8888;
    static double resultado;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP iniciado en el puerto " + puerto);
            socket.setBroadcast(true);
            while (true) {
                byte[] buffer = new byte[MAX_LENGTH];
                DatagramPacket paqueteCliente = new DatagramPacket(buffer, buffer.length);

                // Esperamos a recibir un paquete del cliente
                socket.receive(paqueteCliente);

                InetAddress direccionCliente = paqueteCliente.getAddress();
                int puertoCliente = paqueteCliente.getPort();

                // Convertimos el contenido del paquete a Strings
                String num1 = new String(paqueteCliente.getData(), 0, paqueteCliente.getLength());
                socket.receive(paqueteCliente);
                String num2 = new String(paqueteCliente.getData(), 0, paqueteCliente.getLength());
                socket.receive(paqueteCliente);
                String op = new String(paqueteCliente.getData(), 0, paqueteCliente.getLength());

                System.out.println("Recibidos los valores: " + num1 + ", " + num2 + ", " + op);

                if (num1.equalsIgnoreCase(FIN)) {
                    System.out.println("Servidor cerrado");
                    break;
                }

                String respuesta;
                double resultado = 0;
                switch (op) {
                    case "+":
                        resultado = Double.parseDouble(num1) + Double.parseDouble(num2);
                        respuesta = Double.toString(resultado);
                        break;
                    case "-":
                        resultado = Double.parseDouble(num1) - Double.parseDouble(num2);
                        respuesta = Double.toString(resultado);
                        break;
                    case "*":
                        resultado = Double.parseDouble(num1) * Double.parseDouble(num2);
                        respuesta = Double.toString(resultado);
                        break;
                    case "/":
                        resultado = Double.parseDouble(num1) / Double.parseDouble(num2);
                        respuesta = Double.toString(resultado);
                        break;
                    default:
                        respuesta = "Operación no válida";
                        System.out.println(respuesta);
                        break;
                }

                // Convertimos la respuesta a un array de bytes y la enviamos al cliente
                byte[] respuestaBytes = ("el resultado de tu operación es: "+respuesta).getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(respuestaBytes, respuestaBytes.length,
                        direccionCliente,
                        puertoCliente);
                socket.send(paqueteRespuesta);
                System.out.println("Enviada respuesta: " + respuesta);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ServidorUdp.ChatGPTUDP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPServer {
    private static final int MAX_LENGTH = 65535; // Tamaño máximo del buffer
    public static final String MENSALE_SALIR = "bye";

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int port = 8888;

        try (DatagramSocket ds = new DatagramSocket(port)) {
            while (true) {
                // Aceptamos la conexión de un cliente
                byte[] bufferServer = new byte[MAX_LENGTH];
                DatagramPacket p = new DatagramPacket(bufferServer, bufferServer.length);
                ds.receive(p);
                int portCliente = p.getPort();
                InetAddress addressCliente = p.getAddress();

                // Creamos un hilo para atender a este cliente
                ClientThread clientThread = new ClientThread(ds, addressCliente, portCliente);
                clientThread.start();
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientThread extends Thread {
        private DatagramSocket ds;
        private InetAddress addressCliente;
        private int portCliente;

        public ClientThread(DatagramSocket ds, InetAddress addressCliente, int portCliente) {
            this.ds = ds;
            this.addressCliente = addressCliente;
            this.portCliente = portCliente;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Recibimos mensaje del cliente
                    byte[] bufferServer = new byte[MAX_LENGTH];
                    DatagramPacket p = new DatagramPacket(bufferServer, bufferServer.length);
                    ds.receive(p);
                    String msgCliente = new String(p.getData(), 0, p.getLength()).replace("\n", "");
                    System.out.println("Cliente manda: " + msgCliente);

                    if (msgCliente.equalsIgnoreCase(MENSALE_SALIR)) {
                        break;
                    }

                    // Enviamos mensaje al cliente
                    String mensajeServer = "Server manda: " + sc.nextLine() + "\n";
                    byte[] bufferCliente = mensajeServer.getBytes();
                    p = new DatagramPacket(bufferCliente, bufferCliente.length, addressCliente, portCliente);
                    ds.send(p);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

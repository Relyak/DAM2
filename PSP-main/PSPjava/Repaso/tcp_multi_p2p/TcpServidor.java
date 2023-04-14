package Repaso.tcp_multi_p2p;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpServidor {
    static final String BIENVENIDA = "HOLA BUENAS";
    static final String ADIOS = "adios_server";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int puerto = Integer.parseInt(args[0]);
        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Escuchando en el puerto " + puerto);
            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Ha entrado :" + socketCliente.getInetAddress());
                new Thread(() -> {
                    entablarConexion(socketCliente);
                }).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void entablarConexion(Socket sCli) {
        // Para leer el cliente
        DataOutputStream outTCP;
        try {
            outTCP = new DataOutputStream(sCli.getOutputStream());

            // Para escribir al cliente
            DataInputStream inTCP = new DataInputStream(sCli.getInputStream());

            outTCP.writeUTF(BIENVENIDA);// mensaje bienvenida
            outTCP.flush();// flush para limpiar
            String mensajeCliente = "";
            do {
                // lee mensaje del cliente
                mensajeCliente = inTCP.readUTF();
                System.out.println("Cliente :" + sCli.getInetAddress() + " manda: " + mensajeCliente);
                // pide por teclado para enviar a cliente
                System.out.println("Escribe el mensaje a enviar al cliente");
                mensajeCliente = sc.nextLine();
                // envia el mensaje del cliente
                outTCP.writeUTF(mensajeCliente);
            } while (!mensajeCliente.equalsIgnoreCase(ADIOS));
            inTCP.close();
            outTCP.close();
            sCli.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

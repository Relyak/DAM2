package Tcp.EjemplosTCPMultithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Servidor {
    private static final String MENSAJE_SALIR = "Chao";
    private static final int port = 8888;
    public static void main(String[] args) {
        
        ServerSocket server;
        //Recibirá por parámetros la ip y el puerto
        // 0 = ip - 1 = port
        String msgHola = "Soy el servidor y te doy la bienvenida";
        //int port = Integer.parseInt(args[0]);
        
        try {

            //Instanciamos objeto ServerSocket
            server = new ServerSocket(port);

            //Está escuchando continuamente
            while (true) {
                
                //Server está a la escucha y acepta la conexión
                Socket connCliente = server.accept();
                
                //Utilizamos thread para cada cliente un hilo
                new Thread(() -> {

                    //A través de un método realizamos la conexión
                    newCliente(connCliente,msgHola);

                });
            
            }
            
        } catch (UnknownHostException h) {
            h.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        };
    }

    public static void newCliente (Socket conCliente, String msgHola) {
        try {

            System.out.println("El cliente: |"+conCliente.getInetAddress()+"| ha entrado al servidor");

            //Encargado de enviar
            DataOutputStream outTCP = new DataOutputStream(conCliente.getOutputStream());

            //Encargado de leer
            DataInputStream inTCP = new DataInputStream(conCliente.getInputStream());

            //Devuelvo el mensaje de bienvenida y limpio la salida
            outTCP.writeUTF(msgHola);
            outTCP.flush();

            //Primero lee el mensaje luego escribe en pantalla
            String msgCliente;
            do {

                msgCliente = inTCP.readUTF();
                outTCP.writeUTF(msgCliente);

            } while (!msgCliente.equalsIgnoreCase(MENSAJE_SALIR));
            
            //Avisa quien se ha desconectado
            System.out.println("El cliente: |"+conCliente.getInetAddress()+"| Ha cerrado la sesión");

            inTCP.close();
            outTCP.close();
            conCliente.close();
        }
        catch (IOException i) {
            i.printStackTrace();
        };
    }

}
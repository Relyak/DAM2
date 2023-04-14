package Tcp.EjemplosTCPMultithread;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    
    public static final String MENSAJE_SALIR = "Chao";
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    
        //Recibirá por parámetros la ip y el puerto
        // 0 = ip - 1 = port
        //String ip = args[0];
        String ip="192.168.1.137";
        //int port = Integer.parseInt(args[1]);
        int port = 8888;
        
        try {

            //Realiza la conexion
            Socket conn = new Socket(ip,port);

            //Encargado de enviar
            DataOutputStream outTCP = new DataOutputStream(conn.getOutputStream());

            //Encargado de leer
            DataInputStream inTCP = new DataInputStream(conn.getInputStream());

            //Cliente lee la bienvenida con
            System.out.println("Server dice: "+inTCP.readUTF());

            String msg,msgRecibido;
            do {

                //Envía mensaje al servidor
                msg = sc.nextLine();
                outTCP.writeUTF(msg);
                
                //Lee y muestra en pantalla el mensaje recibido del servidor
                msgRecibido = inTCP.readUTF();
                System.out.println("Servidor: "+msgRecibido);

            } while (!msg.equalsIgnoreCase(MENSAJE_SALIR));

            //Cerramos todas las conexciones, etc
            inTCP.close();
            outTCP.close();
            conn.close();
            sc.close();
            
        } catch (UnknownHostException h) {
            h.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        };

    }

}
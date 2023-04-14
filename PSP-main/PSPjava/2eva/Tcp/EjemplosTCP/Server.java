package Tcp.EjemplosTCP;

import java.io.BufferedOutputStream;
import java.io.IOException; 
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket server;
        try {
            //creo servidor, le doy el puerto 8888 y la ip es mi ip
            server = new ServerSocket(8888);
            //acepto un cliente
            Socket connCliente = server.accept();
            //Creo un buffer para establecer la comunicacion entre el cliente
            BufferedOutputStream out = new BufferedOutputStream(connCliente.getOutputStream());
            //escribo en ese buffer 
            out.write("Bienvenido a MONDONGO".getBytes());
            //cierro
            out.close();
            connCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package Tcp.EjemplosTCP;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {
    public static void main(String[] args) {
        try {
            //creo socket, ip y puerto
            Socket con = new Socket("192.168.1.137", 8000);
            BufferedOutputStream out = new BufferedOutputStream(con.getOutputStream());
            out.write("mondongo".getBytes());
            out.close();
            con.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BienvenidoServidor {
    public static void main(String[] args) {
       int puerto =Integer.parseInt(args[0]);
       String mensaje=args[1];
        try {
            ServerSocket server= new ServerSocket(puerto);
            Socket connCliente = server.accept();
            BufferedOutputStream out= new BufferedOutputStream(connCliente.getOutputStream());
        
            out.write(mensaje.getBytes());
            out.close();
            connCliente.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
    }
}

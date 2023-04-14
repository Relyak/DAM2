import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SaludoMulti {
    public static void main(String[] args) {
       //para que funcione cd psp/2trim/apuntes/comunicaciones
       //java SaludoMulti.java pan1 pan2 pan3 
        try {
            ServerSocket server= new ServerSocket(1234);
            Socket connCliente = server.accept();
            BufferedOutputStream out= new BufferedOutputStream(connCliente.getOutputStream());
            for(int i=0;i<args.length;i++){
                out.write(("bienvenido: "+args[i]+"\n").getBytes());
            }
            
            out.close();
            connCliente.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
    }
}

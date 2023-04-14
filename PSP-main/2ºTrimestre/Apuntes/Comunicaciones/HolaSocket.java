import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class HolaSocket {
    private static final String MENSAJE = "xddd \n";

    public static void main(String[] args) {
        try {
            Socket con = new Socket("192.168.222.131", 1234);
            //con el socket puedo escribir y leer, inputstream outputstream 
            BufferedOutputStream out= new BufferedOutputStream(con.getOutputStream());
            
            out.write(MENSAJE.getBytes());
            out.close();
            con.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
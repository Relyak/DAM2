import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Sender {
    //para ejecutar esto hace falta entrar dentro de lacarpeta git/psp/2trimestre/apuntes/comunicaciones
    //y hacer java Sender.java "parametro1 p2 p3"
    public static void main(String[] args) {
        try {
            Socket con = new Socket("192.168.222.131", 1234);
            //con el socket puedo escribir y leer, inputstream outputstream 
            BufferedOutputStream out= new BufferedOutputStream(con.getOutputStream());
            for(int i=0;i<args.length;i++){
                out.write(args[i].getBytes());
            }
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
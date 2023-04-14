import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class HolaCliente {
    private static final Object MENSAJE_FIN = "bye";

    public static void main(String[] args) {
        try {
            String ip = args[0];
            int port = Integer.parseInt(args[1]);

            Socket con = new Socket(ip, port);
            DataOutputStream outputTCP = new DataOutputStream(con.getOutputStream());
            DataInputStream inputTCP = new DataInputStream(con.getInputStream());

            System.out.println("Server dice: " + inputTCP.readUTF());

            String msg;
            Scanner sc = new Scanner(System.in);
            do {
                msg = sc.nextLine();
                outputTCP.writeUTF(msg);
                String msgMod = inputTCP.readUTF();
                System.out.println("El server dice: " + msgMod);
                // aqui pensar
            } while (!msg.equals(MENSAJE_FIN));
            inputTCP.close();
            outputTCP.close();
            con.close();
            sc.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

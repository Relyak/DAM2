package ServidorTCP.Basico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.1.141", 8000);
            System.out.println("Connected to server");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = in.readLine();
            System.out.println("Server said: " + line);

            out.println("Hello from client");

            out.close();
            in.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

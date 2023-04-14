package ServidorTCP.Basico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started, waiting for a client");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Hello from server");

            String line = in.readLine();
            System.out.println("Client said: " + line);

            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ServidorTCP.MultiHilo;

import java.io.*;
import java.net.*;

import java.io.*;
import java.net.*;

public class TCPServerMulti {
    static class ClientHandler extends Thread {
        private Socket clientSocket;
        private InetAddress clientAddress;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            this.clientAddress = clientSocket.getInetAddress();
        }

        public void run() {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                output.println("Hello, you are connected to the server. Start chatting...");

                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println("Client " + clientAddress + ": " + line);
                    System.out.print("Server: ");

                    line = System.console().readLine();
                    output.println(line);

                    if (line.equalsIgnoreCase("bye")) {
                        break;
                    }
                }

                clientSocket.close();
                System.out.println("Client " + clientAddress + " disconnected.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server is listening on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from " + clientSocket.getRemoteSocketAddress());

            ClientHandler handler = new ClientHandler(clientSocket);
            handler.start();
        }
    }
}

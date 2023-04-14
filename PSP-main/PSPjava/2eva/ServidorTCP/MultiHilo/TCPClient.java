package ServidorTCP.MultiHilo;
import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        String hostname = "192.168.1.141";
        int port = 8000;
        //se conecta al puerto
        Socket socket = new Socket(hostname, port);
        //crea un objeto para leer
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //crea un objeto para escribir
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        //
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Connected to server. Start chatting...");

        String line;
        //bucle para leer linea lee input de servidor mientras no sea nulo
        while ((line = input.readLine()) != null) {
            System.out.println("Server: " + line);
            System.out.print("Client: ");
            line = userInput.readLine();
            //escribe en linea 
            output.println(line);

            if (line.equalsIgnoreCase("bye")) {
                break;
            }
        }

        socket.close();
        System.out.println("Connection closed.");
    }
}

package Repaso.DamHttpServer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpServer {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int puerto = Integer.parseInt(args[0]);//primero puerto
        String nombreArchivo = args[1];//segundo el nombre del archivo
        try {
            // creacion del socket servidor
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto: " + puerto);
            while (true) {
                // aceptar peticiones de los clientes
                Socket socketCliente = serverSocket.accept();
                System.out.println("Ha entrado :" + socketCliente.getInetAddress());
                new Thread(() -> {
                    // entablar conexion con el cliente
                    entablarConexion(socketCliente, nombreArchivo);
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void entablarConexion(Socket sCli, String ruta) {
        try {
            // lectura de la peticion del cliente
            File archivo = new File(ruta);
            // si el archivo existe se envia al cliente
            if (archivo.exists()) {
                BufferedReader fileReader = new BufferedReader(new FileReader(archivo));
                StringBuilder contenidoBuilder = new StringBuilder();
                String linea;
                while ((linea = fileReader.readLine()) != null) {
                    contenidoBuilder.append(linea + "\n");
                }
                // cierre del archivo
                fileReader.close();
                // contenido del archivo
                String contenido = contenidoBuilder.toString();

                // respuesta al cliente
                String respuesta = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: " + contenido.length() + "\r\n"
                        + "\r\n"
                        + contenido;
                // envio de la respuesta al cliente
                PrintWriter out = new PrintWriter(sCli.getOutputStream(), true);
                out.println(respuesta);
            }
        } catch (IOException e) {
        }
    }

}

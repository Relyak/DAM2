package Repaso.cifradoCesarPro;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class CifradoServidor {
    static final String BIENVENIDA = "HOLA BUENAS";
    static final String ADIOS = "adios_server";
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int puerto = Integer.parseInt(args[0]);
        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Escuchando en el puerto " + puerto);
            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Ha entrado :" + socketCliente.getInetAddress());
                new Thread(() -> {
                    entablarConexion(socketCliente);
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void entablarConexion(Socket sCli) {
        // Para leer el cliente
        DataOutputStream outTCP;
        try {
            outTCP = new DataOutputStream(sCli.getOutputStream());
            // Para escribir al cliente
            DataInputStream inTCP = new DataInputStream(sCli.getInputStream());
            outTCP.writeUTF(BIENVENIDA);// mensaje bienvenida
            outTCP.flush();// flush para limpiar
            String mensajeCliCifrado = "";
            String mensajeSerDescifrado="";
            String codigoCifrado="";
            do {
                // lee mensaje del cliente
                mensajeCliCifrado = inTCP.readUTF();
                System.out.println("Cliente :" + sCli.getInetAddress() + " manda este mensaje cifrado: " + mensajeCliCifrado);
                codigoCifrado = inTCP.readUTF();
                System.out.println("Cliente :" + sCli.getInetAddress() + " manda este codigo para decifrado: " + codigoCifrado);
                mensajeSerDescifrado=descifrarCesar(mensajeCliCifrado,Integer.parseInt(codigoCifrado));
                String enviar="Soy el servidor, recibi tu mensaje "+mensajeCliCifrado+" tu mensaje descifrado es: "+mensajeSerDescifrado;
                // System.out.println("Escribe el mensaje a enviar al cliente");
                // mensajeCliente = sc.nextLine();
                // envia el mensaje del cliente
                outTCP.writeUTF(enviar);
            } while (!mensajeCliCifrado.equalsIgnoreCase(ADIOS));
            inTCP.close();
            outTCP.close();
            sCli.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static String descifrarCesar(String mensaje, int clave) {
        // Para descifrar el mensaje cifrado, simplemente se aplica la rotación inversa
        return cifrarCesar(mensaje, -clave);
    }

    public static String cifrarCesar(String mensaje, int rotacion) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);

            // Cifrar solo los caracteres alfabéticos
            if (Character.isLetter(caracter)) {
                // Determinar la posición del caracter en el alfabeto
                int posicion = caracter - (Character.isUpperCase(caracter) ? 'A' : 'a');

                // Aplicar la rotación y ajustar la posición para que esté dentro del alfabeto
                posicion = (posicion + rotacion + 26) % 26;

                // Convertir la posición de vuelta a un caracter y agregarlo al resultado
                caracter = (char) (posicion + (Character.isUpperCase(caracter) ? 'A' : 'a'));
            }

            resultado.append(caracter);
        }

        return resultado.toString();
    }
    

}

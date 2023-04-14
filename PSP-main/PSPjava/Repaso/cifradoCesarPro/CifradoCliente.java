package Repaso.cifradoCesarPro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CifradoCliente {
    public static void main(String[] args) {
        String ip = args[0];
        int puerto = Integer.parseInt(args[1]);
        final String DESPEDIDA = "adios_cli";
        Scanner sc = new Scanner(System.in);
        try {
            Socket socketCli = new Socket(ip, puerto);

            // para escribir el server
            DataOutputStream outTCP = new DataOutputStream(socketCli.getOutputStream());
            // para leer en el server
            DataInputStream inTCP = new DataInputStream(socketCli.getInputStream());
            // Lee el mensaje de bienvenida del servidor
            System.out.println(inTCP.readUTF());
            String msg = "";
            String msgCi="";
            String rotaciones="";
            String msgServidor = "";
            do {
                // Creo el mensaje "cliente" para enviar a servidor
                System.out.println("Escribe el mensaje para cifrar: ");
                msg = sc.nextLine();
                System.out.println("Dime las rotaciones:");
                rotaciones=sc.nextLine();
                msgCi=cifrarCesar(msg, Integer.parseInt(rotaciones));
                System.out.println("Tu mensaje cifrado es: "+msgCi);
                // envio al servidor mi mensaje
                outTCP.writeUTF(msgCi);
                outTCP.flush();
                outTCP.writeUTF(rotaciones);
                outTCP.flush();
                // ahora necesito recibir el mensaje del servidor
                msgServidor = inTCP.readUTF();
                System.out.println(msgServidor);

            } while (!msg.equalsIgnoreCase(DESPEDIDA));
            inTCP.close();
            outTCP.close();
            socketCli.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

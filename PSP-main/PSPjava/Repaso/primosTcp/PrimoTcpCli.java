package Repaso.primosTcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PrimoTcpCli {
    public static void main(String[] args) {
        /*¿Eres un primo? Crea un programa Multihread que reciba por parámetro el puerto en el que escucha en TCP. Cada Thread recibe un número
       , calcula si es primero y lo devuelve al cliente.*/
       String ip=args[0];
       int puerto= Integer.parseInt(args[1]);
       final String DESPEDIDA="adios_cli";
       Scanner sc= new Scanner(System.in);
       try{
           Socket socketCli= new Socket(ip,puerto);

           //para escribir el server
           DataOutputStream outTCP= new DataOutputStream(socketCli.getOutputStream());
           //para leer en el server
           DataInputStream inTCP= new DataInputStream(socketCli.getInputStream());
           //Lee el mensaje de bienvenida del servidor
           System.out.println(inTCP.readUTF());
           String msg="";
           String msgServidor="";
           do{
               //Creo el mensaje "cliente" para enviar a servidor
               System.out.println("Escribe al servidor: ");
               msg=sc.nextLine();
               //envio al servidor mi mensaje
               outTCP.writeUTF(msg);
               outTCP.flush();
               //ahora necesito recibir el mensaje del servidor
               msgServidor=inTCP.readUTF();
               System.out.println(msgServidor);
               
           }while(!msg.equalsIgnoreCase(DESPEDIDA));
           inTCP.close();
           outTCP.close();
           socketCli.close();
       }catch(UnknownHostException e){
           e.printStackTrace();
       }catch(IOException e){
           e.printStackTrace();
       }


   }
}

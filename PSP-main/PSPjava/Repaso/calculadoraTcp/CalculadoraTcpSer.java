package Repaso.calculadoraTcp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalculadoraTcpSer {
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
        int num1=0,num2=0;
        String op="";
        double result=0;
        String mensajeEnv="";
        // Para leer el cliente
        DataOutputStream outTCP;
        try {
            outTCP = new DataOutputStream(sCli.getOutputStream());
            // Para escribir al cliente
            DataInputStream inTCP = new DataInputStream(sCli.getInputStream());
            outTCP.writeUTF(BIENVENIDA);// mensaje bienvenida
            outTCP.flush();// flush para limpiar
            String mensajeCliente = "";
            do {
                //lee primer numero
                mensajeCliente = inTCP.readUTF();
                System.out.println("Cliente :" + sCli.getInetAddress() + " manda numero 1: " + mensajeCliente);
                num1=Integer.parseInt(mensajeCliente);
                //lee segundo numero
                mensajeCliente = inTCP.readUTF();
                System.out.println("Cliente :" + sCli.getInetAddress() + " manda numero 2: " + mensajeCliente);
                num2=Integer.parseInt(mensajeCliente);
                //recibe la operacion
                mensajeCliente = inTCP.readUTF();
                System.out.println("Cliente :" + sCli.getInetAddress() + " manda la operación: " + mensajeCliente);
                op=mensajeCliente;
                op=op.toLowerCase();
                // calcula la operacion
                switch(op){
                    case "+":
                    result=num1+num2;
                    mensajeEnv="El resultado es: "+result;
                    outTCP.writeUTF(mensajeEnv);
                    break;
                    case "-":
                    result=num1-num2;
                    mensajeEnv="El resultado es: "+result;
                    outTCP.writeUTF(mensajeEnv);
                    break;
                    case "/":
                    result=(double)num1/num2;
                    mensajeEnv="El resultado es: "+result;
                    outTCP.writeUTF(mensajeEnv);
                    break;
                    case "*":
                    result=num1*num2;
                    mensajeEnv="El resultado es: "+result;
                    outTCP.writeUTF(mensajeEnv);
                    break;
                }
            } while (!mensajeCliente.equalsIgnoreCase(ADIOS));
            inTCP.close();
            outTCP.close();
            sCli.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



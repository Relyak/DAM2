package Repaso.calculadoraUdp;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CalculadoraUdpCli {

    static Scanner sc = new Scanner(System.in);
    static String FIN = "fin";
    static String direccionIP = "192.168.1.255";
    static String localhost = "127.0.0.1";
    static int puerto=8888;

    public static void main(String[] args) {
        String num1 = "",num2="",op="",msgServ="";
        try {
            SocketUDP ds = new SocketUDP(puerto,direccionIP);
            do { 
                System.out.println("Dime el primer numero");
                num1 = sc.nextLine();
                System.out.println("Dime el segundo numero");
                num2 = sc.nextLine();
                System.out.println("Dime la operacion");
                op = sc.nextLine();
                ds.enviarMensaje(num1);
                ds.enviarMensaje(num2);
                ds.enviarMensaje(op);
                if(!num1.equalsIgnoreCase(FIN)){
                msgServ=ds.recibirMensaje();
                System.out.println(msgServ);
                System.out.println();
                }else{
                    break;
                }
            } while (!num1.equalsIgnoreCase(FIN));

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

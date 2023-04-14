package ServidorUdp.P2P;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
//el cliente manda un hola inmundo al server
public class EjemploCliente {
    public static void main(String[] args) {
        //para hacer comunicacion udp usamos DatagramSocket y DatagramPocket
        //Lo meto en el try para no tener que cerrarlo
        try (DatagramSocket ds = new DatagramSocket();){
            //creo un array de buffer y cojo sus bytes            
            byte buffer[] = "Hola inmundo".getBytes();
            //IP Y PUERTO 
            String ip="192.168.1.136";
            int puerto = 8888;
            //Aqui la conexion
                DatagramPacket p = new DatagramPacket(
                    buffer,//mando buffer
                    buffer.length,//tamanio buffer
                    InetAddress.getByName(ip),//Ip a que quiero entrar
                    puerto//puerto
                    );
                
                ds.send(p);//enviar
            //catch
            } catch (UnknownHostException e) {             
                e.printStackTrace();
            } catch (SocketException e) {      
            e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
    }
}

package ServidorUdp.ChatUDPNoMulti;
    import java.io.IOException;
    import java.net.DatagramPacket;
    import java.net.DatagramSocket;
    import java.net.InetAddress;
    import java.net.SocketException;
    import java.util.Scanner;
    //esto es un chat pero solo permite un usuario a la vez
    public class ChatServer {
        private static final int MAX_LENGTH = 65535;//tamanio maximo del buffer
        public static final String MENSALE_SALIR = "bye";
    
        public static Scanner sc = new Scanner(System.in);
    
        public static void main(String[] args) {
            //int port = Integer.parseInt(args[0]);
            int port = 8888;
            //Variables información del cliente
            int portCliente;
            InetAddress addressCliente;
    
            //Buffer utilizados para enviar / recibir mensajes
            byte bufferServer[] = new byte[MAX_LENGTH];
            byte bufferCliente[] = new byte[MAX_LENGTH];
    
            try (DatagramSocket ds = new DatagramSocket(port);) {
                String msgCliente="",mensajeServer="";
                 //creo un objeto que recibe el buffer del servidor mensaje y tamanio del mensaje
                DatagramPacket p = new DatagramPacket(bufferServer,bufferServer.length);
                while (!(msgCliente.equalsIgnoreCase(MENSALE_SALIR) || mensajeServer.equalsIgnoreCase(MENSALE_SALIR))) {
                    //el servidor recibe mensaje del cliente
                    ds.receive(p);
                    msgCliente = new String (p.getData(),0,p.getLength()).replace("\n","");
                    System.out.println("Cliente manda: "+msgCliente);
                    
                    //Envia mensajes al cliente
                    mensajeServer ="Server manda:"+sc.nextLine()+"\n";
                    bufferCliente = mensajeServer.getBytes();
    
                    //Recibimos información del cliente
                    portCliente = p.getPort();
                    addressCliente = p.getAddress();
    
                    //Creamos el nuevo paquete y los enviamos
                    p = new DatagramPacket(bufferCliente, bufferCliente.length, addressCliente, portCliente);
                    ds.send(p);
                }
    
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
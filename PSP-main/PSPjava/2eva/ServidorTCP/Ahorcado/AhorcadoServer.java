package ServidorTCP.Ahorcado;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class AhorcadoServer{

    public static final int TOTAL_VIDAS = 6;
    private static final String PALABRA = Palabra.getPalabra().toLowerCase();
    public static final String MENSAJE_BIENVENIDA = "Bienvenido al ahorcado tienes: "+TOTAL_VIDAS+" vidas \nIntroduce una letra!\n";

    public static void main(String[] args) {
        
        //int port = args[0];
        int port = 8000;
        ServerSocket server;    
        try {

            //Instanciamos objeto ServerSocket
            server = new ServerSocket(port);

            //Está escuchando continuamente
            while (true) {
                
                //Server está a la escucha y acepta la conexión
                Socket conCliente = server.accept();

                //Utilizamos thread para cada cliente un hilo
                new Thread(() -> {

                    //A través de un método realizamos la conexión
                    newGame(conCliente);

                }).start();
                
            }
            
        } catch (UnknownHostException h) {
            h.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        };
    }

    public static void newGame(Socket conCliente) {

        //Servidor muestra en pantalla cuando un cliente entra
        System.out.println("El cliente: |"+conCliente.getInetAddress()+"| ha entrado al servidor");

        //Se otorgan las vidas iniciales
        int vidas = TOTAL_VIDAS;
        boolean gano=false;
        //Creamos el array que utilizaremos para comprobar y almacenar, acto seguido le damos valor
        char[] estadoPalabra = new char[PALABRA.length()];
        System.out.println("La palabra secreta es: "+PALABRA);
        for (int i = 0; i < estadoPalabra.length; i++) {
            estadoPalabra[i] = '_';
        }
        
        //Encargado de enviar
        DataOutputStream enviarTCP = new DataOutputStream(null);
        //Encargado de leer
        DataInputStream leerTCP = new DataInputStream(null);

        //Asignamos enlace con el cliente y enviamos mensaje bienvenida
        try {
            enviarTCP = new DataOutputStream(conCliente.getOutputStream());

            leerTCP = new DataInputStream(conCliente.getInputStream());
            
            enviarTCP.writeUTF(MENSAJE_BIENVENIDA);
            enviarTCP.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        while (vidas >= 0 && !gano) {
            try  {

                // Recibir la letra del jugador a través del socket
                String letra = leerTCP.readUTF().toLowerCase();


                //Comprueba si la letra introducida está
                boolean encontrado = false;
                for (int i = 0; i < PALABRA.length(); i++) {
                    if (PALABRA.charAt(i) == letra.charAt(0)) {
                        estadoPalabra[i] = letra.charAt(0);
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    vidas--;
                }
                
                //Mientras falte letras se repetira el bucle while
                gano = true;
                for (int i = 0; i < estadoPalabra.length; i++) {
                    if (estadoPalabra[i] == '_') {
                        gano = false;
                        break;
                    } 
                }
                

                // Enviar el estado actual del juego a todos los jugadores
                enviarEstado(estadoPalabra, vidas, encontrado, enviarTCP);
            

                // Si alguien ganó, detener el juego
                if (gano) {
                    enviarTCP.writeUTF("¡Felicidades, ganaste!");
                    enviarTCP.flush();
                    cerrarConexion(conCliente);
                    break;
                }
            
                // Si se acabaron las vidas detener el juego
                if (vidas <= 0) {
                    enviarTCP.writeUTF("Perdiste. La palabra correcta era: " + PALABRA);
                    enviarTCP.flush();
                    cerrarConexion(conCliente);
                    break;
                } 
                
                //Comunica al cliente si la partida continúa
                enviarTCP.writeUTF("La partida continúa");
                enviarTCP.flush();

            } catch (IOException e) {
                e.printStackTrace();
            };
            
        }

        

    }

    //Envia el estado actual del jugador
    public static void enviarEstado(char[] estadoPalabra, int vidas, boolean gano, DataOutputStream outTCP) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < estadoPalabra.length; i++) {
            sb.append(estadoPalabra[i] + " ");
        }

        //Muestra resultado si te equivocaste o acertaste
        String estadoEleccion="";
        if (gano) {
            estadoEleccion = "Has acertado";
        } else {
            estadoEleccion = "Te has equivocado";
        }

        //Envío el estado de la partida
        try {
            
            
            outTCP.writeUTF("Palabra: "+sb.toString()+"\n"+"Vidas: "+String.valueOf(vidas)+"\n"+estadoEleccion);
            dibujarAhorcado(vidas, outTCP);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Dibuja el ahorcado
    public static void dibujarAhorcado(int vidas, DataOutputStream outTCP) throws IOException {
        switch (vidas) {
            case 6:
                outTCP.writeUTF(" _________     \n|         |    \n|              \n|              \n|              \n|              \n");
                break;
            case 5:
                outTCP.writeUTF(" _________     \n|         |    \n|         0    \n|              \n|              \n");
                break;
            case 4:
                outTCP.writeUTF(" _________     \n|         |    \n|         0    \n|         |    \n|              \n|              \n");
                break;
            case 3:
                outTCP.writeUTF(" _________     \n|         |    \n|         0    \n|        /|    \n|              \n|              \n");
                break;
            case 2:
                outTCP.writeUTF(" _________     \n|         |    \n|         0    \n|        /|\\ \n|             \n|              \n");
                break;
            case 1:
                outTCP.writeUTF(" _________     \n|         |    \n|         0    \n|        /|\\ \n|        /     \n|              \n");
                break;
            case 0:
                outTCP.writeUTF(" _________    \n|         |    \n|         0    \n|        /|\\ \n|        / \\ \n|              \n");
                break;
        }
    }

    public static void cerrarConexion(Socket connCliente) throws IOException {
        connCliente.close();
    }

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AhorcadoServer {
    private static final int PUERTO = 8000;
    private static final String PALABRA = "ahorcado";
    private static int vidas = 6;
    private static boolean gano = false;
    private static char[] estadoPalabra = new char[PALABRA.length()];
    private static List<HiloJugador> jugadores = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < estadoPalabra.length; i++) {
            estadoPalabra[i] = '_';
        }

        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            while (true) {
                Socket socket = servidor.accept();
                HiloJugador jugador = new HiloJugador(socket);
                jugadores.add(jugador);
                jugador.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class HiloJugador extends Thread {
        private Socket socket;
        private BufferedReader entrada;
        static private PrintWriter salida;

        public HiloJugador(Socket socket) {
            this.socket = socket;
            try {
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                salida = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (vidas >= 0 && !gano) {
                try {
                    // Recibir la letra del jugador a través del socket
                    String letra = entrada.readLine();
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
                    gano = true;
                    for (int i = 0; i < estadoPalabra.length; i++) {
                        if (estadoPalabra[i] == '_') {
                            gano = false;
                            break;
                        }
                    }

                // Enviar el estado actual del juego a todos los jugadores
                    for (HiloJugador jugador : jugadores) {
                        jugador.enviarEstado(estadoPalabra, vidas, gano);
                    }

                // Si alguien ganó, detener el juego
                    if (gano) {
                        for (HiloJugador jugador : jugadores) {
                            jugador.enviarMensaje("¡Felicidades, ganaste!");
                            jugador.cerrarConexion();
                        }
                        break;
                    }
                // Si se acabaron las vidas detener el juego
                    if (vidas <= 0) {
                        for (HiloJugador jugador : jugadores) {
                            jugador.enviarMensaje("Perdiste. La palabra correcta era: " + PALABRA);
                            jugador.cerrarConexion();
                        }
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        public void enviarEstado(char[] estadoPalabra, int vidas, boolean gano) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < estadoPalabra.length; i++) {
                sb.append(estadoPalabra[i] + " ");
            }
            salida.println(sb.toString());
            salida.println(vidas);
            dibujarAhorcado(vidas);
            salida.println(gano);
        }

        public void enviarMensaje(String mensaje) {
            salida.println(mensaje);
        }

        public void cerrarConexion() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static void dibujarAhorcado(int vidas) {
            switch (vidas) {
                case 6:
                    salida.println(" _________     ");
                    salida.println("|         |    ");
                    salida.println("|              ");
                    salida.println("|              ");
                    salida.println("|              ");
                    salida.println("|              ");
                    break;
                case 5:
                    salida.println(" _________     ");
                    salida.println("|         |    ");
                    salida.println("|         0    ");
                    salida.println("|              ");
                    salida.println("|              ");
                    salida.println("|              ");
                    break;
                case 4:
                    salida.println(" _________     ");
                    salida.println("|         |    ");
                    salida.println("|         0    ");
                    salida.println("|         |    ");
                    salida.println("|              ");
                    salida.println("|              ");
                    break;
                case 3:
                    salida.println(" _________     ");
                    salida.println("|         |    ");
                    salida.println("|         0    ");
                    salida.println("|        /|    ");
                    salida.println("|              ");
                    salida.println("|              ");
                    break;
                case 2:
                    salida.println(" _________     ");
                    salida.println("|         |    ");
                    salida.println("|         0    ");
                    salida.println("|        /|\\ ");
                    salida.println("|              ");
                    salida.println("|              ");
                    break;
                case 1:
                    salida.println(" _________     ");
                    salida.println("|         |    ");
                    salida.println("|         0    ");
                    salida.println("|        /|\\ ");
                    salida.println("|        /     ");
                    salida.println("|              ");
                    break;
                case 0:
                    salida.println(" _________     ");
                    salida.println("|         |    ");
                    salida.println("|         0    ");
                    salida.println("|        /|\\ ");
                    salida.println("|        / \\ ");
                    salida.println("|              ");
                    break;
            }
        }
    }
}
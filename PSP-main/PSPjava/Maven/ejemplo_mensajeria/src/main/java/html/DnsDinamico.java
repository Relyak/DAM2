package html;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DnsDinamico {
  // VARIABLES
  //Dado un HashMap con dos cadenas. Las claves son cadenas de texto y los valores son el resultado del comando md5sum. Haz un programa que lo recorra y reporte que hash han sido bien calculados.
  public static final String DIR = "www.cual-es-mi-ip.net";
  public static final int USUARIO = 0;
  public static final int PASSWORD = 1;
  public static final int DOMINIO = 2;
  public static Pattern pattern = Pattern.compile("El IP [0-9\\.]*");
  private static final String FREEDNS = "freedns.afraid.org";
  private static final int PORT = 443;
  public static String miIp = "";
  public static Matcher matcher;

  public static void main(String[] args) throws Exception {
    miIp = extraerIp();// traigo mi IP
    // Variables que extraigo de la clase SP
    String password = Sp.getPalabra().get(PASSWORD);
    String username = Sp.getPalabra().get(USUARIO);
    String dominio = Sp.getPalabra().get(DOMINIO);

    System.out.println("NUEVA IP: " + miIp);

    SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    Socket socketDns = sslsocketfactory.createSocket(FREEDNS, PORT);
    PrintWriter outDns = new PrintWriter(socketDns.getOutputStream());
    BufferedReader in = new BufferedReader(new InputStreamReader(socketDns.getInputStream()));
    //hazme una interf
    // PARA ACTUALIZAR NECESITO PASARLE MIS CREDENCIALES CODIFICADAS
    String credenciales = username + ":" + password;
    String credencialesBase64 = Base64.getEncoder().encodeToString(credenciales.getBytes());// AQUI LAS CODIFICA
    // PETICION
    outDns.print("GET /nic/update?hostname=" + dominio + "&myip=" + miIp + " HTTP/1.1\r\n");
    outDns.print("Host: " + FREEDNS + "\r\n");
    outDns.print("Authorization: Basic " + credencialesBase64 + "\r\n");
    outDns.print("\r\n");
    outDns.flush();
    int cont = 0;
    // LEER PARA COMPROBAR SI SE HA HECHO CORRECTAMENTE
    String leerLineaDns;
    while ((leerLineaDns = in.readLine()) != null) {
      System.out.println(leerLineaDns);
      if (leerLineaDns.equalsIgnoreCase("")) {
        cont++;
        if (cont == 3)
          break;
      }
    }
    System.out.println("Llegue al final");
    in.close();
    socketDns.close();
    outDns.close();
  }

  public static String extraerIp() throws UnknownHostException, IOException {

    SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    Socket socketIp = sslsocketfactory.createSocket(DIR, PORT);
    PrintWriter outIp = new PrintWriter(socketIp.getOutputStream(), true);
    // manda un get para conseguir mi IP
    outIp.println("GET / HTTP/1.1");
    outIp.println("Host: " + DIR);
    outIp.println();
    outIp.flush();
    // Leo lo que tiene el socket
    BufferedReader inIp = new BufferedReader(new InputStreamReader(socketIp.getInputStream()));
    String leerLineaIp;
    while ((leerLineaIp = inIp.readLine()) != null) {
      matcher = pattern.matcher(leerLineaIp);
      while (matcher.find()) {
        System.out.println(matcher.group());
        miIp = matcher.group();
      }
    }
    // quito todo lo extra para quedarme con la ip
    miIp = miIp.replaceAll("El IP ", "");
    miIp = miIp.replaceAll(" ", "");
    inIp.close();
    socketIp.close();
    outIp.close();
    System.out.println("Terminé de extraer la ip");
    return miIp;
  }
}

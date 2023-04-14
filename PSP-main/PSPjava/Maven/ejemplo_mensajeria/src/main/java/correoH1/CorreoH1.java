package correoH1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLSocketFactory;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class CorreoH1 {
    public static Pattern pattern = Pattern.compile("<h1.?>(.?)</h1>");//para buscar los h1
    public static Matcher matcher;
    public static final int NOMBRE_AUTH = 0;
    public static final int NOMBREP_AUTH = 1;
    public static final String DIR="icutum.es";
    public static final int PORT=443;
    public static void main(String[] args) throws Exception {
        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket socketHTML = sslsocketfactory.createSocket(DIR, PORT);
        PrintWriter peticionHTML = new PrintWriter(socketHTML.getOutputStream(), true);
        // manda un get para conseguir mi IP
        peticionHTML.println("GET / HTTP/1.1");
        peticionHTML.println("Host: " + DIR);
        peticionHTML.println();
        peticionHTML.flush();
        // Leo lo que tiene el socket
        BufferedReader inIp = new BufferedReader(new InputStreamReader(socketHTML.getInputStream()));
        String leerLinea;
        String h1="";
        while ((leerLinea = inIp.readLine()) != null) {
          matcher = pattern.matcher(leerLinea);
          while (matcher.find()) {
            System.out.println(matcher.group());
            h1 = matcher.group();
          }
        }
        // quito todo lo extra para quedarme con la ip
        h1 = h1.replace("<h1> ", "");
        h1 = h1.replace(" </h1>", "");
        System.out.println(h1);
        inIp.close();
        socketHTML.close();
        peticionHTML.close();
        try {
            // Instanciamos
            Email email = new SimpleEmail();
            // Eliges el sitio, puerto
            email.setHostName("smtp.educa.madrid.org");
            email.setSmtpPort(587);
            // Te autorizas, no se deben cifrar los datos
            email.setAuthentication(Sp.getPalabra().get(NOMBRE_AUTH), Sp.getPalabra().get(NOMBREP_AUTH));
            email.setStartTLSEnabled(true);
            // Correo emisor en este caso puedo escribir lo que sea pero tiene que ser
            // @educa.madrid.org
            email.setFrom("TuPapiExtraeLosH1@educa.madrid.org");
            // Subtitulo y mensaje
            email.setSubject("Mando h1");
            email.setMsg(h1+"aqui tienes tu h1 puta mierda");
            // receptor y método de envío
            // email.addTo("francisco.lopez82@educa.madrid.org");
            //email.addTo("jorge.duenas@educa.madrid.org");
            email.addTo("mariosmrfp@gmail.com");
            email.send();
            System.out.println("Enviado con amor!");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
   
}

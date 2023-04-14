package correo;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.mail.MessagingException;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Hello world!
 *
 */
public class App {
    public static final int NOMBRE_AUTH = 0;
    public static final int NOMBREP_AUTH = 1;

    public static void main(String[] args) throws MessagingException, IOException {
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
            email.setFrom("kayler.borges@educa.madrid.org");

            // Subtitulo y mensaje
            
            email.setSubject("Duda con PSP, correo enviado desde programa java");
            File file = new File("C:\\Users\\rocas\\Documents\\jiji.txt");
            String words = "";
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                words += scanner.nextLine() + "\n";
            }
            System.out.println(words);
            email.setMsg(
                    "Hola Jorge,te mando esto desde mi programa java por si sale el texto mal identado,  he intentando hacer lo de psp pero no me sale, cuando uso curl -H funciona pero "
                            +
                            "con java me devuelve un bad request 400, te dejo el codigo por si me pudieras dar alguna pista \n "
                            + words);
            scanner.close();
            // receptor y método de envío
            // email.addTo("francisco.lopez82@educa.madrid.org");
            email.addTo("jorge.duenas@educa.madrid.org");

            email.send();
            System.out.println("Email sent!");

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}

package correoCuadrado;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.mail.MessagingException;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class CorreoCuadrado {
    public static int alto = 10;
    public static int ancho = 10;
    public static String correo = "kayler.borges@educa.madrid.org";
    public static final int POS_USUARIO = 0;
    public static final int POS_PASS = 1;

    public static void main(String[] args) throws MessagingException, IOException {
        hacerCuadrado(alto, ancho, correo);
    }

    public static void hacerCuadrado(int alto, int ancho, String correo) throws FileNotFoundException {
        String cuadradoLleno = cuadradoLleno(ancho, alto);
        String cuadradoVacio = cuadradoVacio(ancho, alto);
        try {
            // Instanciamos
            Email email = new SimpleEmail();
            // Eliges el sitio, puerto
            email.setHostName("smtp.educa.madrid.org");
            email.setSmtpPort(587);
            // Te autorizas, no se deben cifrar los datos
            email.setAuthentication(Sp.getPalabra().get(POS_USUARIO), Sp.getPalabra().get(POS_PASS));
            email.setStartTLSEnabled(true);
            // Correo emisor en este caso puedo escribir lo que sea pero tiene 
            email.setFrom(correo);
            // Subtitulo y mensaje
            email.setSubject("Perdón Jorge me olvidé de cambiar el correo");
            email.setMsg(
                    "Buenas noches Jorge,te pido perdón porque estaba probando algo en java con los cuadrados y me olvidé de cambiar el correo te mando un cuadrado de compensación y te dejo el código por si lo quieres ver.\n"
                            + cuadradoLleno + "\n" + cuadradoVacio);
            // receptor y método de envío
            // email.addTo("francisco.lopez82@educa.madrid.org");
            email.addTo("josemariajimenez2000@gmail.com");
            // email.addTo("jorge.duenas@educa.madrid.org");
            email.send();
            System.out.println("Email enviado con amor!");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static String cuadradoVacio(int ancho, int alto) {
        String cuadrado = "";
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (i == 0 || i == alto - 1) {
                    cuadrado += "x";
                } else if (j == 0 || j == ancho - 1) {
                    cuadrado += "x";
                } else {
                    cuadrado += "o";
                }
            }
            cuadrado += "\n";
        }
        return cuadrado;
    }

    public static String cuadradoLleno(int ancho, int alto) {
        String cuadrado = "";
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                cuadrado += "*";
            }
            cuadrado += "\n";
        }
        return cuadrado;
    }
}

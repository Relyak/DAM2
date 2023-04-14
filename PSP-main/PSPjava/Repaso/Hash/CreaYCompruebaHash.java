package Repaso.Hash;
import java.util.HashMap;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class CreaYCompruebaHash {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce una cadena para calcular su hash MD5: ");
        String cadena = sc.nextLine();
        String cadenaHash = transformarAHash(cadena);
        System.out.println("El hash de la cadena '" + cadena + "' es: " + cadenaHash);
        // Crear un HashMap con dos cadenas
        HashMap<String, String> map = new HashMap<>();
        map.put(cadena, cadenaHash);
        map.put("prueba", "noEsUnHash");
        // Recorrer el HashMap y verificar los hashes
        for (String key : map.keySet()) {
            // Obtener el hash de la cadena actual del HashMap
            cadenaHash = comprobadorHash(key);
            // Comparar el hash obtenido con el del HashMap
            if (cadenaHash.equals(map.get(key))) {
                System.out.println("El hash de la cadena '" + key + "' ha sido bien calculado.");
            } else {
                System.out.println("El hash de la cadena '" + key + "' no ha sido bien calculado.");
            }
        }
    }
    private static String comprobadorHash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5"); // Instanciar un objeto MD5
        byte[] messageDigest = md.digest(input.getBytes()); // Calcular el digest del input dado
        // Convertir el array de bytes a hexadecimal para obtener el hash MD5
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString(); // Devolver el hash MD5 como una cadena de texto
    }
    public static String transformarAHash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(str.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
package Repaso.cifradoCesarPro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class CifradoCesar {
    public static int[] rotaciones = {-5,19,-18,10};

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Mensaje: ");
        String mensaje = "Juan";

        String cifrado = cifrarCesar(mensaje, rotaciones);

        System.out.println("Mensaje cifrado: " + cifrado);
        System.out.println("Mensaje descifrado: " + descifrarCesar(cifrado, rotaciones));
        String ip="127.0.0.1";//args[0];
        int puerto= 8000;//Integer.parseInt(args[1]);
    }

    public static String cifrarCesar(String mensaje, int[] rotaciones) {
        String cifrado = "";
        for (int i = 0; i < mensaje.length(); i++) {
            char c = mensaje.charAt(i);
            int rotacion = rotaciones[i % rotaciones.length];
            if (rotacion < 0) {
                rotacion = ((rotacion % 26) + 26) % 26;
            }
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) (((c - base + rotacion) % 26) + base);
            }
            cifrado += c;
        }
        return cifrado;
    }
    
    public static String descifrarCesar(String cifrado, int[] rotaciones) {
        String descifrado = "";
        for (int i = 0; i < cifrado.length(); i++) {
            char c = cifrado.charAt(i);
            int rotacion = rotaciones[i % rotaciones.length];
            if (rotacion < 0) {
                rotacion = ((rotacion % 26) + 26) % 26;
            }
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) (((c - base + (26 - rotacion)) % 26) + base);
            }
            descifrado += c;
        }
        return descifrado.trim();
    }
}
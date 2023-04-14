package ServidorTCP.Ahorcado;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Palabra {
    public static String getPalabra() {
        List<String> words = new ArrayList<>();
        try {
            File file = new File("/home/rocas/Desktop/git/PSP/PSPjava/2eva/ServidorTCP/Ahorcado/palabras.txt"); 
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("archivo no encontrado");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        String randomWord = words.get(randomIndex);

        return randomWord;
    }
}    


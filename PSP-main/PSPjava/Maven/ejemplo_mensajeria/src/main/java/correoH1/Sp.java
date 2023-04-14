package correoH1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sp {
    public static List<String> getPalabra() {
        List<String> words = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\rocas\\Documents\\mogus_escrito.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return words;
    }
}

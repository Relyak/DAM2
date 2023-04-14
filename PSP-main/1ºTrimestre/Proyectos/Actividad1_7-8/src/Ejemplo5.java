import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejemplo5 {

	public static void main(String[] args) throws IOException {
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(in);
		try {
			String texto;
			System.out.println("Introduce una cadena...") ;
			texto= br.readLine();
			System.out.println("Cadena escrita: "+texto);
			in.close();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}
	}
}


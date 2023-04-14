import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Actividad1_7 {

	public static void main(String[] args) throws IOException {
		File directorio = new File(".\\bin");
		System.out.println("Empezamos el proceso...");
		ProcessBuilder pb=new ProcessBuilder("java","Ejemplo5");
		pb.directory(directorio);
		System.out.printf("Directorio de trabajo: %s\n",pb.directory());
		try {
			File out=new File("Salida.txt");
			File err=new File("Error.txt");
			File bat=new File("fichero-entrada.txt");
			pb.redirectOutput(out);
			pb.redirectError(err);
			pb.redirectInput(bat);
			Process p=pb.start();
			InputStream is=p.getInputStream();
			int c;
			while((c=is.read())!=-1) {
				System.out.print((char)c);
			}
			is.close();
			System.out.println("Fin del proceso");
			System.out.println("Resultado de la ejecucion= "+p.waitFor());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

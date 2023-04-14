package UT1Ejem;
import java.io.*;

public class Ejemplo5 {

	public static void main(String[] args) throws IOException {

		File directorio = new File("C:\\Users\\12137\\AppData\\Roaming\\Code\\User\\workspaceStorage\\17cc3bd431fe1fac893b4f4aa176e7f0\\redhat.java\\jdt_ws\\PSPjava_a78831b6\\bin");
		ProcessBuilder pb = new ProcessBuilder("java", "EjemploLectura");

		pb.directory(directorio);

		// se ejecuta el proceso
		Process p = pb.start();

		// escritura -- envia entrada 
		OutputStream os = p.getOutputStream();
		os.write("Hola Manuel\n".getBytes());
		os.flush(); // vac�a el buffer de salida

		// lectura -- obtiene la salida
		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1)
			System.out.print((char) c);
		is.close();

		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			InputStream er = p.getErrorStream();
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));
			String liner = null;
			while ((liner = brer.readLine()) != null)
				System.out.println("ERROR >" + liner);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}// Ejemplo5

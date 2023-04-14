package UT1Ejem;
import java.io.*;

public class Ejemplo3 {
  public static void main(String[] args) throws IOException {
    String path ="C:\\Users\\12137\\AppData\\Roaming\\Code\\User\\workspaceStorage\\17cc3bd431fe1fac893b4f4aa176e7f0\\redhat.java\\jdt_ws\\PSPjava_a78831b6\\bin";
	//creamos objeto File al directorio donde esta Ejemplo2
	File directorio = new File(path);	

	//El proceso a ejecutar es Ejemplo2			
	ProcessBuilder pb = new ProcessBuilder("java", "Ejemplo2");

    //se establece el directorio donde se encuentra el ejecutable
    pb.directory(directorio);
        
	System.out.printf("Directorio de trabajo: %s%n",pb.directory());

    //se ejecuta el proceso
	Process p = pb.start();

   //obtener la salida devuelta por el proceso
	try {
		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1)
			System.out.print((char) c);
		is.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		
  }
}// Ejemplo3
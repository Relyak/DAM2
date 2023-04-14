package UT1Ejem;
import java.io.File;
import java.io.IOException;
 
public class Ejemplo7 { 
  public static void main(String args[]) throws IOException {
    ProcessBuilder pb = new ProcessBuilder("CMD","/C" ,"C:\\Users\\12137\\AppData\\Roaming\\Code\\User\\workspaceStorage\\17cc3bd431fe1fac893b4f4aa176e7f0\\redhat.java\\jdt_ws\\PSPjava_a78831b6\\bin\\trabaja1_6");
    
    File fOut = new File("salida.txt");
    File fErr = new File("error.txt");
 
    pb.redirectOutput(fOut);
    pb.redirectError(fErr); 
    pb.start(); 
  }
}// Ejemplo7
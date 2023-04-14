package ejemplos;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class trabaja4 {
    public static void main(String args[]) throws IOException{
            Scanner sc = new Scanner(System.in);
            char numero;
            System.out.println("Introduce un numero");
         numero=(char) System.in.read();
        String entre=String.valueOf(numero);
        int man=numero;
        
        
        if(man==13){
            System.out.println("null");
            System.exit(1);
        }
        
        if(man>=65 && man<=122){
            System.out.println("cadenar");
            System.exit(2);
        }
        int entro=Character.getNumericValue(numero);
        if(entro<0){
            System.out.println("no puede menor 0");
            System.exit(3);
        }else{
            System.exit(0);
        }

    }


}

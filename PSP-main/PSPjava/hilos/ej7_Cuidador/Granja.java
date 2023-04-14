package ej7_Cuidador;

//Granja2: Modifica el ejercicio anterior. 
//Cada vez que un animal entre al recipiente y coma, el recipiente se quedará vacío. 
//Cuando un animal entra a comer y está vacío dice que "Está vacío". 
//Crea otro Thread Cuidador/a que cata 100 milisegundo (Sin números mágicos) rellene el recipiente si está vacío.

public class Granja {

    private static final int MAX_ANIMALES = 4;
    public static void main(String[] args) {
        
        // Comedero uno a la vez
        Comedero comedero = new Comedero();
        
         // Se inica el cuidador
         Thread cuidador = new Thread(new Cuidador(comedero));
         cuidador.start();

        // Animales
        Thread[] animales = new Thread[MAX_ANIMALES];
        
        for (int i = 0; i < MAX_ANIMALES; i++) {
            animales[i] = new Thread(new Animal("animal "+ (i+1), comedero));
            animales[i].start();
        }
       

    }
}


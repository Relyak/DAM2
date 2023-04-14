package ej6_Granja;

//Granja: Escribe un programa en Java que simule una granja. 
//Cada animal debe ser un thread que se mueve y hace sonidos. 
//Después de hacer un número aleatorios de movimientos y sonidos intentará comer. 
//Para comer debe acceder al recipiente de comida. Solo puede acceder cada vez uno.

public class Granja{
	
	private static final int MAX_ANIMALES = 4;
	
	public static void main(String[] args) {
		// Comedero uno a la vez
        Comedero comedero = new Comedero();
        // Animales
        Thread[] animales = new Thread[MAX_ANIMALES];

        for (int i = 0; i < MAX_ANIMALES; i++) {
            animales[i] = new Thread(new Animal("animal "+ (i+1), comedero));
            animales[i].start();
        }
    }
}


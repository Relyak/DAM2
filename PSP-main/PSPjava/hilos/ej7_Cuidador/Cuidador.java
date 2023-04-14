package ej7_Cuidador;

public class Cuidador implements Runnable {

    private Comedero comedero;
    private final int ESPERAR=1000;
    
    public Cuidador(Comedero comedero) {
        this.comedero = comedero;
    }

    @Override
    public void run() {
       while(true) {
            synchronized (comedero) {
                // Rellena el comedero
            	if(!comedero.estaLleno()) {
            		comedero.llenar();
            		System.out.println("El cuidador está llenando el comedero...");
            	}
            	 // Sale
                comedero.salir();
                comedero.notifyAll();
                try {
                    Thread.sleep(ESPERAR);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               
            }
        }
    }

}

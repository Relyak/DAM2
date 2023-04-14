package ut2_Tra;

public class Hijos implements Runnable {
	private int o;


	private static final int NUMERO_HIJOS=5;
    private static final int NUMERO_SALUDOS=2;

    int id;

    public Hijos (int id) {
        this.id=id;
    }

	public void run() {

            for (int i=0;i<NUMERO_SALUDOS;i++) {
			System.out.println("Soy el hijo"+id);
            }
            
	}

	//Para usar el hilo creo la clase UsaHilo:

	public static void main(String[] args){
		
		for(int i=0;i<4;i++){
			Hijos hs = new Hijos(i);
			Thread t = new Thread(hs);
			t.start();
			new Thread(new Hijos(i)).start();
		}
		
		
		
		for (int i=0; i<5;i++)
			System.out.println("Hola soy principal ..");
	}
}

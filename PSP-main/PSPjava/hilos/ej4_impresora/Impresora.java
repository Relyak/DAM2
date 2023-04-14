package ej4_impresora;

public class Impresora implements Runnable{
	
	private int id;
	private static final int USUARIOS=5;
	private Object objeto= new Object();
	private final int dormir=1000;
	private static boolean imprimir=true;
	public Impresora(int id) {
		this.id=id;
	}
	
	@Override
	public void run() {
		if(consulta()) {
			synchronized(objeto) {
				ocupado();
			}
		}	
	}
	
	public void ocupado() {
		imprimir=false;
		System.out.println("Entro: "+id);
		try {
			Thread.sleep(dormir);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("salgo: "+id);
		imprimir=true;
		
	}
	public boolean consulta() {
		
		return imprimir;
	}

	public static void main(String[] args) {
		Thread[] user=new Thread[USUARIOS];
		for(int i=0; i<user.length;i++) {
			Runnable runnable = null;
			runnable=new Impresora(i);
			user[i]=new Thread(runnable);
			user[i].start();
		}
		for(int i=0; i<user.length;i++) {
			try {
				user[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}



}

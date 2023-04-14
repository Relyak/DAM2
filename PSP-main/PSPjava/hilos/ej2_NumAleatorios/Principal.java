package ej2_NumAleatorios;

//Productor-consumidor: 
//Implementa un programa en Java que utilice la técnica de productor-consumidor 
//para compartir datos entre threads. 
//El productor debe generar números aleatorios y el consumidor debe imprimirlos en pantalla.

public class Principal implements Runnable{
	
	public boolean consumidor;
	private static boolean nuevoNum=true;
	private static int num;
	private static Object cerrojo=new Object();
	private static final int NUMHILOS=2;
	
	public Principal(boolean consumidor) {
		this.consumidor=consumidor;
	}
	
	@Override
	public void run() {
		while(true) {
			if(consumidor) {
				mandando();
			}else {
				recibiendo();
			}
		}
	}
	
	private void mandando() {
		synchronized(cerrojo) {
			if(!nuevoNum) {
				System.out.println("NUEVO NUM");
				num=(int)(Math.random()*100);
				nuevoNum=true;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				cerrojo.notifyAll();
				try {
					cerrojo.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void recibiendo() {
		synchronized(cerrojo) {
			if(nuevoNum) {
				System.out.println(num);
				nuevoNum=false;
			}else {
				cerrojo.notifyAll();
				try {
					cerrojo.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	public static void main(String[] args) {
		Thread[] hilos = new Thread[NUMHILOS];
		
		for(int i=0; i<hilos.length;i++) {
			Runnable runnable = null;
			
			if(i!=0) {
				runnable = new Principal(true);
			}else {
				runnable = new Principal(false);
			}
			
			hilos[i] = new Thread(runnable);
			hilos[i].start();
		}
		
		for(int i=0; i<hilos.length;i++) {
			try {
				hilos[i].join();
			}catch(Exception e){
			}
		}
	}
}

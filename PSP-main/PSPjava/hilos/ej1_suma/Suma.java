package ej1_suma;

//Suma de números en paralelo: Escribe un programa en Java que genere un array de 1000 números aleatorio 
//y los sume en paralelo utilizando 4 threads. Sin números mágicos. Cada thread debe sumar una parte del array 
//y el resultado final debe ser la suma de todas las partes.

public class Suma implements Runnable{
	
	private static final int HILOS=4;
	private static final int TAM=1000;
	private static final int MAX_ALEATORIO=100;
	private static int[] aleatorio=new int[TAM];
	private static int[] subTotales=new int[HILOS];
	private int id,inicio, fin;
	private int sum=0;
	
	public Suma(int id,int inicio, int fin, int[] n,int[]subTotal) {
		this.id=id;
		this.inicio=inicio;
		this.fin=fin;
	}
	
	@Override
	public void run() {
		for(int i=inicio; i<fin; i++) {
			sum=sum+aleatorio[i];
		}
		System.out.println(sum);
		subTotales[id]=sum;
	}

	//main: le pide a cada hijo el subtotal y lo suma (cada hijo tiene su cuenta, puedes hacer un método getSubtotal())
	public int getSubtotal() {
		for(int i=inicio; i<fin; i++) {
			sum=sum+aleatorio[i];
		}
		System.out.println(sum);
		return sum;
	}
		
	public static void main(String[] args) {
		int ini=0;
		int result=TAM/HILOS;
		int fin=result;
		Thread[] suma=new Thread[HILOS];
		//main: Genera un array de 1000 aleatorios
		for(int i=0; i<TAM; i++) {
			aleatorio[i]=(int)(Math.random()*MAX_ALEATORIO);
		}
		
		for(int i=0; i<HILOS; i++) {
			//main: Genera N Thread distribuyendo el trabajo (Eso lo has hecho bien con inicio y fin)
			//Ten en cuenta que a cada hijo le debes pasar inicio, fin y el array entero.
			Runnable runnable=new Suma(i,ini,fin,aleatorio,subTotales);
			suma[i] = new Thread(runnable);
			//main: arranca los hilos
			suma[i].start();
			ini=fin;
			fin+=result;
		}

		for(int i=0; i<suma.length; i++) {
			try {
				//main: espera a que terminen cada hijos
				suma[i].join();
			}catch (Exception e){
				System.out.println("Error");
			}			
		}
		int total=0;
		for(int i=0; i<suma.length; i++) {
			total=total+subTotales[i];			
		}
		System.out.println("total: "+total);
	}
}

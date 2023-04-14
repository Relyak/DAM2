package ej5_Primos;

import java.util.ArrayList;
import java.util.List;

//Cálculo de números primos: 
//Crea un programa en Java que calcule todos los números primos entre 1 y 1000 utilizando threads para paralelizar el procesamiento y los almacene en un array. 
//Cada thread debe procesar una parte del rango y el resultado final debe ser la combinación de todas las partes.

public class Principal implements Runnable{
	private static final int MAX=100;
	private static List<Integer> primos=new ArrayList<Integer>();
	private static int[] num=new int[MAX];
	private static final int NUMHILOS=4;
	private int inicio,fin,id;
	private Object objeto=new Object();
	
	@Override
	public void run() {
		synchronized(objeto) {
			System.out.println("Entra "+id);
			for(int i=inicio; i<fin; i++) {
				int n=num[i]+1;
				if(EsPrimo(n)) {
					primos.add(n);
					System.out.println("soy: "+id+ " - "+(i));
					
				}	
			}
			System.out.println("sale "+ id);
		}
	}
	
	public Principal(int id,int inicio, int fin, int[]num) {
		this.id=id;
		this.inicio=inicio;
		this.fin = fin;
	}
	
	public static void main(String[] args) {
		int ini=0;
		int div=MAX/NUMHILOS;
		int fin=div;
		
		//carga el array
		for(int i=1; i<=MAX; i++) {
			num[i-1]=i;
		}
		
		Thread[] hilos = new Thread[NUMHILOS];
		
		//crea los hilos
		for(int i=0; i<NUMHILOS; i++) {
			Runnable runnable = new Principal(i,ini,fin,num);
			hilos[i]= new Thread(runnable);
			hilos[i].start();
			ini=fin;
			fin=ini+div;
		}
		//el main espera a que finalicen los hilos
		for(int i=0; i<NUMHILOS; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int[] ordenado = primos.stream().mapToInt(i -> i).toArray();
		burbuja(ordenado);
		//se imprime 
		for (int i = 0; i <primos.size(); i++) {
			 System.out.print((ordenado[i])+" ");
		}

	}
	
	private boolean EsPrimo(int n) {
		synchronized(objeto) {
	        if (n <= 1) {
	            return false;
	        }
	        for (int i = 2; i <= Math.sqrt(n); i++) {
	            if (n % i == 0) {
	                return false;
	            }
	        }
	        return true;
		}
    }
	public static void burbuja(int[] A) {
        int i, j, aux;
        for (i = 0; i < A.length - 1; i++) {
            for (j = 0; j < A.length - i - 1; j++) {                                                              
                if (A[j + 1] < A[j]) {
                    aux = A[j + 1];
                    A[j + 1] = A[j];
                    A[j] = aux;
                }
            }
        }
	}
}
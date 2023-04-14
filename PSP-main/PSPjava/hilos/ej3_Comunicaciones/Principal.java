package ej3_Comunicaciones;

import java.util.Scanner;

//Comunicación de mensajes: 
//Implementa un programa en Java que utilice dos threads para enviar y recibir mensajes. 
//El thread de envío debe pedir al usuario que ingrese un mensaje, 
// y luego enviarlo al thread de recepción.
//El thread de recepción debe imprimir el mensaje recibido.

public class Principal implements Runnable{
	private static Object objeto=new Object();
	private static final int NUMHILOS=2;
	private int id;
	Scanner sc=new Scanner(System.in);
	private static String msg="";
	
	@Override
	public void run() {
		while(true) {
			emisor();
			receptor();
		}
	}
	
	public Principal(int id) {
		this.id=id;
	}
	
	public void emisor() {
		synchronized(objeto) {
			if(id==0) {
				System.out.println("soy el emisor");
				try {
					objeto.notifyAll();
					objeto.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Escribe un mensaje");
				msg=sc.nextLine();
			}else {
			}
		}
	}
	
	public void receptor() {
		synchronized(objeto) {
			if(id==1) {
				System.out.println("Dime un mensaje: ");
				objeto.notifyAll();
				try {
					objeto.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("soy el receptor " +msg);
			}else {
			}
		}
	}
	
	public static void main(String[] args) {
		
		Thread[] hilo=new Thread[NUMHILOS];
		
		for(int i=0; i<hilo.length;i++) {
			Runnable runnable=null;
			runnable=new Principal(i);
			
			hilo[i] = new Thread(runnable);
			hilo[i].start();
		}
		
		for(int i=0; i<NUMHILOS;i++) {
			try {
				hilo[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

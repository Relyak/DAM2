package ej7_Cuidador;

import java.util.Random;

public class Animal implements Runnable {
    private int accion=0;
    private String nombre;
    private Comedero comedero;
    private final Random random = new Random();

    public Animal(String nombre, Comedero comedero) {
        this.nombre = nombre;
        this.comedero = comedero;
    }

    void andar() {
        System.out.println(nombre + " esta andando");
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void sonido() {
        System.out.println(nombre + " esta haciendo un sonido");
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            accion();
        }
    }

    void accion() {
    	accion=(int)(Math.random()*4);
    	if(accion==1) andar();
    	if(accion==2) sonido();
    	if(accion==3) comer();
    }

    void comer() {
        synchronized (comedero) {
            // El animal quiere comer pero no puede si esta en uso o vacio.
            while (!comedero.acceder(nombre)) {
                System.out.println(nombre + " El comedero esta vacio");
                try {
                    comedero.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Come.
            System.out.println(nombre + " esta comiendo...");
            comedero.entrar();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Termina de comer.
            System.out.println(nombre + " termina de comer");
            comedero.salir();
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablaJuegos;

import java.util.Scanner;

/**
 *
 * @author rocas
 */
public class MainJuegos {
  public static Scanner sc = new Scanner(System.in);
    public static JuegosInterface objetoDAO = GaleriaJuegos.getJuegosDao();
    public static void main(String[] args) {
        menu();
    }
    public static void menu() {
        int opc;
        do {
            opciones();
            opc = sc.nextInt();
            switch (opc) {
                case 0:
                    break;
                case 1:
                    mostrarTodo();
                    break;
                case 2:
                    System.out.println("Dime los datos del nuevo juego");
                    anadir();
                    break;
                case 3:
                    System.out.println("Dime el id a borrar");
                    borrado();
                    break;
                case 4:
                    System.out.println("Dime el id para buscar");
                    buscaId();
                    break;
                case 5:
                    System.out.println("Dime el genero para buscar");
                    buscarGenero();
                    break;
                case 6:
                    System.out.println("Dime el nombre para buscar");
                    buscarNombre();
                    break;
                case 7:
                    System.out.println("Dime el id para modificar los datos");
                    modificarValor();
                    break;
            }
        } while (opc != 0);
    }
    public static void anadir() {
        sc.nextLine();
        System.out.println("Nombre:");
        String nom = sc.nextLine();
        System.out.println("Genero:");
        String gen = sc.nextLine();
        System.out.println("Fecha de lanzamiento YYYY-MM-DD:");
        String fec = sc.nextLine();
        JuegosInterface juego = objetoDAO.getNuevoJuego(nom, gen, fec);
    }
    public static void opciones() {
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("OPCIONES");
        System.out.println("0- Salir");
        System.out.println("1- Mostrar tabla");
        System.out.println("2- Agregar valor");
        System.out.println("3- Borrar valor");
        System.out.println("4- Buscar valor por id");
        System.out.println("5- Buscar valor por genero");
        System.out.println("6- Buscar valor por nombre");
        System.out.println("7- Modificar valor");
        System.out.println("-----------------------------");
    }
    public static void mostrarTodo() {
        sc.nextLine();
        objetoDAO.getJuegosTabla();
    }
    private static void borrado() {
        int idBorr = sc.nextInt();
        objetoDAO.delete(idBorr);
    }
    private static void buscaId() {
        int id = sc.nextInt();
        objetoDAO.getJuegosPorId(id);
    }
    private static void buscarNombre() {
        sc.nextLine();
        String nom = sc.nextLine();
        objetoDAO.getJuegosPorNombre(nom);
    }
    private static void buscarGenero() {
        sc.nextLine();
        String gen = sc.nextLine();
        objetoDAO.getJuegosPorGenero(gen);
    }
    private static void modificarValor() {
        int id = sc.nextInt();
        objetoDAO.modificarValor(id);
    }

}

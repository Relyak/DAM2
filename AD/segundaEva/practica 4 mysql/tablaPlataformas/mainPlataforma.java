/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablaPlataformas;

import java.util.Scanner;

public class mainPlataforma {

    public static Scanner sc = new Scanner(System.in);
    public static PlataformaInterface objetoDAO = GaleriaPlataformas.getPlataformaDao();

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
                    System.out.println("Dime los datos de la nueva plataforma");
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
                    System.out.println("Dime la empresa para buscar");
                    buscaEmpresa();
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
        System.out.println("Empresa:");
        String emp = sc.nextLine();
        System.out.println("Fecha de lanzamiento YYYY-MM-DD:");
        String fec = sc.nextLine();
        System.out.println("Id del juego:");
        int id = sc.nextInt();
        PlataformaInterface plataforma = objetoDAO.getNuevaPlataforma(nom, emp, fec, id);
    }

    public static void opciones() {
        System.out.println("-----------------------------");
        System.out.println("OPCIONES");
        System.out.println("0- Salir");
        System.out.println("1- Mostrar tabla");
        System.out.println("2- Agregar valor");
        System.out.println("3- Borrar valor");
        System.out.println("4- Buscar valor por id");
        System.out.println("5- Buscar valor por empresa");
        System.out.println("6- Buscar valor por nombre");
        System.out.println("7- Modificar valor");
        System.out.println("-----------------------------");
    }

    public static void mostrarTodo() {
        sc.nextLine();
        objetoDAO.getPlataformaEntera();
    }

    private static void borrado() {
        int idBorr = sc.nextInt();
        objetoDAO.delete(idBorr);
    }

    private static void buscaId() {
        int id = sc.nextInt();
        objetoDAO.getPlataformaPorId(id);
    }

    private static void buscarNombre() {
        sc.nextLine();
        String nom = sc.nextLine();
        objetoDAO.getPlataformaPorNombre(nom);
    }

    private static void buscaEmpresa() {
        sc.nextLine();
        String emp = sc.nextLine();
        objetoDAO.getPlataformaPorEmpresa(emp);
    }

    private static void modificarValor() {
        int id = sc.nextInt();
        objetoDAO.modificarValor(id);
    }

}

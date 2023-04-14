/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablaJuegos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author rocas
 */
public class JuegosBean implements JuegosInterface {

    //VARIABLES QUE USARE
    private int id;
    private String nombre;
    private String genero;
    private String lanzamiento;
    private java.sql.Connection conexionA = null;
    //METODOS GETTER Y SETTER
    public String getNombre() {
        return nombre;
    }
    public String getGenero() {
        return genero;
    }
    public String getLanzamiento() {
        return lanzamiento;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setLanzamiento(String lanzamiento) {
        this.lanzamiento = lanzamiento;
    }
    //METODO PARA ESTABLECER LA CONEXIÓN
    private java.sql.Connection getConexionPlataformas() {
        //Cargar el driver
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Obtener el driver
        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la clase del Driver");
            return null; //devuelve null si va mal
        }
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull", "root", "");
        } catch (SQLException e) {
            System.out.println("No se puede obtener la conexión");
            return null;
        }
        return conexion;
    }
    //METODOS BUSQUEDA
    //POR ID
    public void getJuegosPorId(int id) {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            //se crea la sentencia
            java.sql.ResultSet resultado;
            resultado = sentencia.executeQuery(
                    "SELECT * FROM juegos WHERE id=" + id);
            while (resultado.next()) {
                id = resultado.getInt("id");
                nombre = resultado.getString("nombre");
                genero = resultado.getString("genero");
                lanzamiento = resultado.getString("lanzamiento");
                System.out.printf("ID: %d NOMBRE: %s GENERO: %s F_LANZ: %s", id,nombre,genero,lanzamiento);
            }
            resultado.close();
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error en SELECT de plataformas por id sobre plataforma");
            System.out.println(e);
        }
    }
    //DEVUELVE TODA LA TABLA
    public void getJuegosTabla() {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            java.sql.ResultSet resultado;
            resultado = sentencia.executeQuery(
                    "SELECT * FROM juegos");
            while (resultado.next()) {
                id = resultado.getInt("id");
                nombre = resultado.getString("nombre");
                genero = resultado.getString("genero");
                lanzamiento = resultado.getString("lanzamiento");
                System.out.printf("ID:%d NOMBRE: %s GENERO: %s F_LANZ: %s \n", id,nombre,genero,lanzamiento);
            }
            resultado.close();
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error en SELECT * FROM juegos " + e);
            System.out.println(e);
        }
    }
    //NOMBRE
    public void getJuegosPorNombre(String nombre) {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            java.sql.ResultSet resultado;
            resultado = sentencia.executeQuery(
                    "SELECT * FROM juegos WHERE nombre='" + nombre + "'");
            while (resultado.next()) {
                id = resultado.getInt("id");
                nombre = resultado.getString("nombre");
                genero = resultado.getString("genero");
                lanzamiento = resultado.getString("lanzamiento");
                System.out.printf("ID: %d NOMBRE: %s GENERO: %s F_LANZ: %s", id,nombre,genero,lanzamiento);
            }
            resultado.close();
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error en SELECT de juegos por nombre " + e);
        }
    }
    //GENERO
    public void getJuegosPorGenero(String genero) {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            java.sql.ResultSet resultado;
            resultado = sentencia.executeQuery("SELECT * FROM juegos WHERE genero='" + genero + "'");
            while (resultado.next()) {
                id = resultado.getInt("id");
                nombre = resultado.getString("nombre");
                genero = resultado.getString("genero");
                lanzamiento = resultado.getString("lanzamiento");
                System.out.printf("ID: %d NOMBRE: %s GENERO: %s F_LANZ: %s", id,nombre,genero,lanzamiento);
            }
            resultado.close();
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error en SELECT de juegos por genero " + e);
        }
    }
    //METODO DE BORRADO
    public void delete(int id) {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            sentencia.execute("DELETE FROM juegos  WHERE id=" + id);
            System.out.println("Borrado");
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error  DELETE sobre juegos " + e);
        };
        this.setConexionA(null);
    }
    //METODO INSERT
    public JuegosInterface getNuevoJuego(String nombre, String genero, String lanzamiento) {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            sentencia.execute(
                    "INSERT INTO juegos(nombre,genero,lanzamiento) VALUES ('" + nombre + "','"
                    + genero + "','" + lanzamiento + "')");
        } catch (SQLException e) {
            System.out.println("Error SQL al insertar juegos" + e);
            return null;
        }
        //Instanciamos nueva plataforma le damos los valores a sus atributos
        JuegosBean juego = new JuegosBean();
        juego.nombre = nombre;
        juego.genero = genero;
        juego.lanzamiento = lanzamiento;
        System.out.println("Insertado");
        //devuelvo el objeto 
        return juego;
    }
    public java.sql.Connection getConexionA() {
        return conexionA;
    }
    public void setConexionA(java.sql.Connection conexionA) {
        this.conexionA = conexionA;
    }
    //METODO MODIFICAR
    public void modificarValor(int id) {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.println("Dime el nuevo nombre");
            String nom = sc.nextLine();
            System.out.println("Dime el nuevo genero ");
            String gen = sc.nextLine();
            System.out.println("Dime el nuevo lanzamiento");
            String dat = sc.nextLine();
            sentencia.executeUpdate("update juegos set nombre='" + nom + "'" + " WHERE id=" + id);
            sentencia.executeUpdate("update juegos set genero='" + gen + "'" + " WHERE id=" + id);
            sentencia.executeUpdate("update juegos set lanzamiento='" + dat + "'" + " WHERE id=" + id);
            System.out.println("Actualizado");
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error en Update" + e);

        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablaPlataformas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author rocas
 */
public class PlataformaBean implements PlataformaInterface {

    //VARIABLES QUE USARE
    private int id;
    private String nombre;
    private String empresa;
    private String lanzamiento;
    private int juegoId;

    private java.sql.Connection conexionA = null;

    //METODOS GETTER Y SETTER
    public String getNombre() {
        return nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getLanzamiento() {
        return lanzamiento;
    }

    public int getJuegoId() {
        return juegoId;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLanzamiento(String lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public void setJuegoId(int juegoId) {
        this.juegoId = juegoId;
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
    public void getPlataformaPorId(int id) {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;

        try {
            sentencia = getConexionA().createStatement();
            //se crea la sentencia
            java.sql.ResultSet resultado;
            resultado = sentencia.executeQuery(
                    "SELECT * FROM plataformas WHERE id=" + id);
            while (resultado.next()) {
                id = resultado.getInt("id");
                nombre = resultado.getString("nombre");
                empresa = resultado.getString("empresa");
                lanzamiento = resultado.getString("lanzamiento");
                juegoId = resultado.getInt("juego_id");
            System.out.printf("ID: %d NOMBRE: %s EMPRESA: %s F_LANZ: %s ID_JUEGO: %d\n", id,nombre,empresa,lanzamiento,juegoId);
            }
            resultado.close();
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error en SELECT de plataformas por id sobre plataforma " + e);
            System.out.println(e);
        }

    }

    //DEVUELVE TODA LA TABLA
    public void getPlataformaTabla() {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            java.sql.ResultSet resultado;
            resultado = sentencia.executeQuery(
                    "SELECT * FROM plataformas");
            while (resultado.next()) {
                id = resultado.getInt("id");
                nombre = resultado.getString("nombre");
                empresa = resultado.getString("empresa");
                lanzamiento = resultado.getString("lanzamiento");
                juegoId = resultado.getInt("juego_id");
                System.out.printf("ID: %d NOMBRE: %s EMPRESA: %s F_LANZ: %s ID_JUEGO: %d\n", id,nombre,empresa,lanzamiento,juegoId);
            }
            resultado.close();
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error en SELECT * FROM plataformas " + e);
        }
    }

    //NOMBRE
    public void getPlataformaPorNombre(String nombre) {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            java.sql.ResultSet resultado;
            resultado = sentencia.executeQuery(
                    "SELECT * FROM plataformas WHERE nombre='" + nombre + "'");

            while (resultado.next()) {
                id = resultado.getInt("id");
                nombre = resultado.getString("nombre");
                empresa = resultado.getString("empresa");
                lanzamiento = resultado.getString("lanzamiento");
                juegoId = resultado.getInt("juego_id");
                System.out.printf("ID: %d NOMBRE: %s EMPRESA: %s F_LANZ: %s ID_JUEGO: %d\n", id,nombre,empresa,lanzamiento,juegoId);
            }
            resultado.close();
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error en SELECT de plataformas por nombre sobre plataforma " + e);
        }
    }

    //EMPRESA
    public void getPlataformaPorEmpresa(String empresa) {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            java.sql.ResultSet resultado;
            resultado = sentencia.executeQuery(
                    "SELECT * FROM plataformas WHERE empresa='" + empresa + "'");
            while (resultado.next()) {
                id = resultado.getInt("id");
                nombre = resultado.getString("nombre");
                empresa = resultado.getString("empresa");
                lanzamiento = resultado.getString("lanzamiento");
                juegoId = resultado.getInt("juego_id");
                System.out.printf("ID: %d NOMBRE: %s EMPRESA: %s F_LANZ: %s ID_JUEGO: %d\n", id,nombre,empresa,lanzamiento,juegoId);
            }
            resultado.close();
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error en SELECT de plataformas por empresa sobre plataforma " + e);
        }
    }
    //METODO DE BORRADO
    public void delete(int id) {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            sentencia.execute("DELETE FROM plataformas  WHERE id=" + id);
            System.out.println("Borrado");
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error  DELETE   sobre plataformas " + e);
        };
        this.setConexionA(null);
    }
    //METODO INSERT
    public PlataformaInterface getNuevaPlataforma(String nombre, String empresa, String lanzamiento, int juegoId) {
        setConexionA(getConexionPlataformas());
        java.sql.Statement sentencia = null;
        try {
            sentencia = getConexionA().createStatement();
            sentencia.execute(
                    "INSERT INTO plataformas(nombre,empresa,lanzamiento,juego_id) VALUES ('" + nombre + "','"
                    + empresa + "','" + lanzamiento + "','" + juegoId + "')");
        } catch (SQLException e) {
            System.out.println("Error SQL al insertar plataforma " + e);
            return null;
        }
        //Instanciamos nueva plataforma le damos los valores a sus atributos
        PlataformaBean plataforma = new PlataformaBean();
        plataforma.nombre = nombre;
        plataforma.empresa = empresa;
        plataforma.lanzamiento = lanzamiento;
        plataforma.juegoId = juegoId;
        System.out.println("Insertado");
        //devuelvo el objeto 
        return plataforma;
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
            System.out.println("Dime la nueva empresa");
            String emp = sc.nextLine();
            System.out.println("Dime el nuevo lanzamiento");
            String dat = sc.nextLine();
            System.out.println("Dime el nuevo id de juego");
            int idJu = sc.nextInt();
            sc.nextLine();
            sentencia.executeUpdate("update plataformas set nombre='" + nom + "'" + " WHERE id=" + id);
            sentencia.executeUpdate("update plataformas set empresa='" + emp + "'" + " WHERE id=" + id);
            sentencia.executeUpdate("update plataformas set lanzamiento='" + dat + "'" + " WHERE id=" + id);
            sentencia.executeUpdate("update plataformas set juego_id=" + idJu + " WHERE id=" + id);
            System.out.println("Actualizado");
            sentencia.close();
            getConexionA().close();
        } catch (SQLException e) {
            System.out.println("Error en Update " + e);

        }
    }
}

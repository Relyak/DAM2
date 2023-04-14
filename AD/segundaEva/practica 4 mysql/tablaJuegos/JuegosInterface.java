/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablaJuegos;

/**
 *
 * @author rocas
 */
public interface JuegosInterface {
//EN INTERFAZ LO QUE TENGO UNOS GET Y SET, METODOS DE BUSQUEDA POR ALGO Y INSERTAR NUEVO DATO
// metodos GET

    public String getNombre();
    public String getGenero();
    public String getLanzamiento();   
     
// metodos SET
    public void setNombre(String nombre);  
    public void setGenero(String genero);
    public void setLanzamiento(String lanzamiento);
// métodos FIND
    public void getJuegosPorId(int id);
    public void getJuegosPorNombre(String nombre);
    public void getJuegosPorGenero(String genero);
    public JuegosInterface getNuevoJuego(String nombre,String genero, String lanzamiento);
    public void getJuegosTabla();
    
  // METODO BORRADO
    public void delete(int id);
   
  // METODO DE MODIFICACION
    public void modificarValor(int id);
}

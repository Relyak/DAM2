/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablaPlataformas;
public interface PlataformaInterface {
 //EN INTERFAZ LO QUE TENGO UNOS GET Y SET, METODOS DE BUSQUEDA POR ALGO Y INSERTAR NUEVO DATO
// metodos GET
  
    public String getNombre();
    public String getEmpresa();
    public String getLanzamiento();   
    public int getJuegoId();
      
// metodos SET
    public void  setNombre(String nombre);  
    public void setEmpresa(String empresa);
    public void setLanzamiento(String lanzamiento);
    public void setJuegoId(int juegosId);
// métodos FIND
    public void getPlataformaPorId(int id);
    public void getPlataformaPorNombre(String nombre);
    public void getPlataformaPorEmpresa(String empresa);
    public PlataformaInterface getNuevaPlataforma(String nombre,String empresa, String lanzamiento, int juegosId);
    public void getPlataformaTabla();
    
  // METODO BORRADO
    public void delete(int id);
   
  // METODO DE MODIFICACION
    public void modificarValor(int id);

}
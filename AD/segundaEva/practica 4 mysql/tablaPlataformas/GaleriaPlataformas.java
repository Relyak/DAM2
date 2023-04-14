/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablaPlataformas;

/**
 *
 * @author rocas
 */
public class GaleriaPlataformas {
    // metodo estatico que devuelve un obj plataformaInterfaz
     
  public static  PlataformaInterface getPlataformaDao(){
      
        return new PlataformaBean();
    }
}

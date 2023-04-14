package persistenciadbemp;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class EmpBorrar {
    public static void main(String [] args){    
        //ID PARA BORRAR
        int idBorrar=7777;
        //MANEJADOR DE ENTIDADES
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaDBempPU");
        EntityManager em = emf.createEntityManager();
        //OPERACION
        em.getTransaction().begin();//INICIO
        Emp emp = em.find(Emp.class, idBorrar);//BUSQUEDA DE ID EN TABLA EMP
        em.remove(emp);//METODO DE BORRADO
        em.getTransaction().commit();//COMMIT PARA GUARDAR
    } 
}

package persistenciadbemp;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class DeptBorrar {
    public static void main(String[] args) {
        //VARIABLES
        int idBorrar=70;
        //MANEJADOR DE TABLAS
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaDBempPU");
        EntityManager em = emf.createEntityManager();
        //OPERACION
        em.getTransaction().begin();//INICIO
        Dept dept = em.find(Dept.class, idBorrar);//BUSCAR ID EN TABLA
        em.remove(dept);//BORRAR SEGUN ID
        em.getTransaction().commit();//GUARDAR
    }
}

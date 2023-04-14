package persistenciadbemp;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class DeptModificar {
    public static void main(String[] args) {
        //VARIABLES A USAR
        int id=70;
        String dname="RECURSOS";
        String loc="VALENCIA";
        //MANEJADOR DE TABLAS
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaDBempPU");
        EntityManager em = emf.createEntityManager();
        //OPERACION
        em.getTransaction().begin();//INICIO
        Dept dept = em.find(Dept.class, id);//BUSCAR ID EN TABLA DEPT
        //INSERCION
        dept.setDname(dname);
        dept.setLoc(loc);
        //COMMIT PARA GUARDAR
        em.getTransaction().commit();
    }
}

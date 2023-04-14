package persistenciadbemp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author osboxes
 */
public class DeptInsertar {
      public static void main(String[] args) {
        //VARIABLES PARA INSERTAR
        int idInsertar=70;
        String dname="RRHH";
        String loc="MADRID";
        Dept dept =new Dept();
        //MANEJADOR DE ENTIDADES 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaDBempPU");
        EntityManager em = emf.createEntityManager();
        //INSERCION DE DATOS
        dept.setDeptno(idInsertar);
        dept.setDname(dname);
        dept.setLoc(loc);
        //OPERACION
        em.getTransaction().begin();//INICIO
        em.persist(dept);//GUARDAR EL OBJETO DE LA TABLA
        em.getTransaction().commit();//COMMIT PARA GUARDAR
        }
    }
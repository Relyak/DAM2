package persistenciadbemp;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rocas
 */
public class PersistenciaDBemp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaDBempPU");
        DeptJpaController dao = new DeptJpaController(emf);
        List<Dept> lista =dao.findDeptEntities();
        for (Dept departamento : lista) {
            System.out.println("nombre departamento: " + departamento.getDname());
        }
    }
}
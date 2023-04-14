package persistenciadbemp;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class MostrarEmp {
    public static void main(String[] args) {
        // CREACION DE MANEJADOR DE TABLAS
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaDBempPU");
        EmpJpaController dao = new EmpJpaController(emf);
        //LISTA DEL OBJETO EMP, BUSQUEDA EN LA TABLA
        List<Emp> lista = dao.findEmpEntities();
        //RECORRER TABLA Y PRINTEAR DATOS CON LOS METODOS GET
        for (Emp tabla : lista) {
            System.out.printf("EMPNO:%d ENAME:%S SAL:%.2f DEPTNO:%d\n",tabla.getEmpno(),tabla.getEname(),tabla.getSal(),tabla.getDeptno());
        }
    }
}

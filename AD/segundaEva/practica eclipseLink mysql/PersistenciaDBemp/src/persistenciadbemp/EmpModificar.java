package persistenciadbemp;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class EmpModificar {
      public static void main(String[] args) {
        //VARIABLES DE LA TABLA EMP
        int idInsertar = 8888;
        String ename = "JUAN";
        String job = "OLVIDARDO";
        int mgr = 7839;
        Date date = new Date();
        date.setYear(00);
        date.setMonth(1);// ESTE METODO CUENTA LOS MESES EMPEZANDO EN 0, MI MES ES FEBRERO(1)
        date.setDate(15);
        BigDecimal sal = new BigDecimal("3000.50");
        BigDecimal comm = new BigDecimal("0.00");
        int deptno = 10;
        //MANEJADOR DE TABLAS
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaDBempPU");
        EntityManager em = emf.createEntityManager();
        //OPERACION
        em.getTransaction().begin();//INICIO
        Emp empleado = em.find(Emp.class, idInsertar);//BUSCAR ID EN CLASE EMP
        //INSERTAR VARIABLES NUEVAS
        empleado.setComm(comm);
        empleado.setDeptno(deptno);
        empleado.setEname(ename);
        empleado.setHiredate(date);
        empleado.setJob(job);
        empleado.setMgr(mgr);
        empleado.setSal(sal);
        em.getTransaction().commit();//COMMIT PARA GUARDAR
    }
}

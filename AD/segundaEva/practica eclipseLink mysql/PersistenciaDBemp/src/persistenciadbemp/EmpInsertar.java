package persistenciadbemp;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class EmpInsertar {
    public static void main(String[] args) throws ParseException {
        //VARIABLES QUE UTILIZARE
        int idInsertar = 8888;
        String ename = "MIGUE";
        String job = "NUEVARDO";
        int mgr = 7839;
        Date date = new Date();
        date.setYear(83);
        date.setMonth(1); //MI MES ES FEBRERO, ESTE METODO EMPIEZA A CONTAR DESDE 0
        date.setDate(15);
        BigDecimal sal = new BigDecimal("1000.50");
        BigDecimal comm = new BigDecimal("0.00");
        int deptno = 10;
        Emp empleado = new Emp();
        //MANEJADOR DE TABLAS
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaDBempPU");
        EntityManager em = emf.createEntityManager();
        //ASIGNO VALORES CON EL SET
        empleado.setEmpno(idInsertar);
        empleado.setComm(comm);
        empleado.setDeptno(deptno);
        empleado.setEname(ename);
        empleado.setHiredate(date);
        empleado.setJob(job);
        empleado.setMgr(mgr);
        empleado.setSal(sal);
        //OPERACION
        em.getTransaction().begin();//INICIO
        em.persist(empleado);//INSERCION
        em.getTransaction().commit();//COMMIT
    }
}

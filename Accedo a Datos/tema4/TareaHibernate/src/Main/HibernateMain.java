/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import HibernateTarea04.Dept;
import HibernateTarea04.Emp;
import HibernateTarea04.Queries;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Antonio
 */
public class HibernateMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        //creamos la conexion y la abrimos
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Queries queries = new Queries();
        List<Dept> deps = queries.consulta("from Dept");
        Dept departament = deps.get(0);

        short numemp = 7840;
        short numdep = 6547;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stringFecha = "2021-01-15";
        Date fecha = sdf.parse(stringFecha);
        BigDecimal salario = BigDecimal.valueOf(1900);
        BigDecimal comision = BigDecimal.valueOf(400);
        Emp newEmpleado = new Emp(numemp, departament, "ANTONIO", "INFORMATICO",
                numdep, fecha, salario, comision);

        queries.instertarEmpleado(newEmpleado);
        //queries.borrarEmpleado(newEmpleado);

        

        System.out.println("------------------------------------------------------------------"
                + "\nLista de tablas EMP y DEPT que visualice empno,ename,dname,sal y loc con HQL\n"
                + "------------------------------------------------------------------");
        //Llamamos a la interfaz SQLquery o createQuery dependiendo de si queremos hacer la consulta en HQL o SQL
        //Creo que esta vez está bien la consulta
        Query q1 = session.createQuery("FROM Emp as empleado INNER JOIN empleado.dept as departamento");
        //Esta consulta retornarán una lista de objetos arrays (Object[]) con valores 
        //escalares para cada columna en la tabla EMP
        List<Object[]> lista = q1.list();//lista de objetos
        for (Object[] objecto : lista) {//bucle para listar las tablas
            Emp empleado = (Emp) objecto[0];
            Dept departamento = (Dept) objecto[1];
            System.out.println("Nombre: " + empleado.getEname() + ", número: " + empleado.getEmpno()
                    + ", salario: " + empleado.getSal() + " , departamento: " + departamento.getDname()
                    + ", localización departamento: " + departamento.getLoc());
        }
        session.close();//se cierra la sesion
        
        //IMPORTANTE CERRAR LA SESION   
        NewHibernateUtil.getSessionFactory().close();
    
    }

}

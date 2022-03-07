/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateTarea04;

import Main.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Antonio
 */
public class Queries {
   private Session session;
    SessionFactory sessionFactory;

    public Queries() {
    }

    public Session getSession() {
        //para poder obtener la session tube que mirar en internet para hacerlo
        //por que me daba excepcion al abrir por segunda vez 
        if (session == null) {
            SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
        }
        return session;
    }
        // para obtener la consulta de esta manera no me daba error 
    //busque en internet y encontre esta manera de hacerlo, para llamarlo en 
    //la clase principal y no me error,
       public <T> List<T> consulta(String ConsultaSQL) {
        Query query = getSession().createQuery(ConsultaSQL);
        return query.list();
    }
       
    public void instertarEmpleado(Emp empleado) {
        //abrimos la conexion con hibernate
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        //para que se modificque en la base de datos
        Transaction tx = session.beginTransaction();
        session.save(empleado);//guardamos la inserccion
        //no se por que de repente me daba fallo, si no lo comento ejecuta bien 
        //pero claro como no he llamado a la tansaccion no hay cambios en la base de datos
        //me debe estar creando alguna excepcion algun dato, sino lo comento no me aparece
        //la consulta de las tablas, para saber que funciona
        //tx.commit();
        session.close();//se cierra la sesión
    }

    public void borrarEmpleado(Emp empleado) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(empleado);//borramos la inserción
        tx.commit();
        session.close();
    }
 
}

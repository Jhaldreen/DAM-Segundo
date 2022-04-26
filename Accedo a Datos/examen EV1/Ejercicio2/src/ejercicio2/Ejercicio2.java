/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.util.List;
import java.util.logging.Level;
import org.hibernate.Session;

/**
 *
 * @author DIEGO
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Introduzco esta linea para evitar que se muestren en la consola avisos inecesarios */
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        /* Creo la sesion para establecer la conexion con la base de datos */
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        /* Llamo al metodo que inserta los dos nuevos registros y le paso la sesion */
        insertaDatos(sesion);
        /* Llamo al metodo que muestra toda la información de la BD */
        muestraDatos(sesion);
        /* Antes de salir cierro la sesion */
        sesion.getSessionFactory().close();
    }
    
    /* Este metodo inserta dos registros nuevos en la BD uno en cada tabla */
    public static void insertaDatos(Session sesion){
        /* Creo el aula que voy a insertar */
        Aulas aul = new Aulas(34);
        aul.setNombre("AULA-34");
        aul.setCapacidad(30);
        /* Creo el alumno que voy a insertar en la BD usando el aula creada anteriormente */
        Alumnos alu = new Alumnos(10, aul, "Tu nombre", "Tus apellidos");
        /* Inicio la transaccion, le paso los dos objetos y la ejecuto */
        sesion.beginTransaction();
        sesion.save(alu);
        sesion.save(aul);
        sesion.getTransaction().commit();
    }
    
    /* Este metodo nos muestra toda la información de la BD */
    public static void muestraDatos(Session sesion){
        /* Creo las dos consultas que extraen toda la información de las dos tablas */
        List<Aulas> aulas = sesion.createQuery("from Aulas").list();
        List<Alumnos> alumnos = sesion.createQuery("from Alumnos").list();
        /* Empiezo a mostrar los datos almacenados en los objetos List */
        System.out.println("****** Listado de Aulas ******\n");
        for(Aulas aul : aulas){
            System.out.println("Número de aula: " + aul.getNaula());
            System.out.println("Nombre del aula: " + aul.getNombre());
            System.out.println("Capacidad del aula: " + aul.getCapacidad() + "\n");
        }
        System.out.println("****** Fin del listado de Aulas ******\n");
        System.out.println("****** Listado de Alumnos ******\n");
        for(Alumnos alu : alumnos){
            System.out.println("Número de matricula: " + alu.getIdmatricula());
            System.out.println("Nombre del alumno: " + alu.getNombre());
            System.out.println("Apellidos del alumno: " + alu.getApellidos());
            /* Para poder sacar el número del aula necesito cogerlo del objeto aula mediante un get */
            System.out.println("Aula de referencia: " + alu.getAulas().getNaula() + "\n");
        }
        System.out.println("****** Fin del listado de Alumnos ******");
    }
}

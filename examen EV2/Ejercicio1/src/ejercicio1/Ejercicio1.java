/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/* Voy añadiendo los import necesarios */
import java.io.File;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.util.ArrayList;

/**
 *
 * @author DIEGO
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* En el examen se les entrega ya el archivo creado con todos los datos */
        /* Este código lo pongo por si la base de datos ya existiera y quisiéramos empezar con ella limpia */
        File fichero = new File("IESBDDB4O");
        fichero.delete();
        /* Creo la base de datos nueva ya que al haber borrado ese archivo previamente me aseguro de que no exista */
        /* Es muy importante que el nomre sea el mismo en ambas lineas, sino serán archivos diferentes y no funciona */
        ObjectContainer baseDatos = Db4oEmbedded.openFile("IESBDDB4O");
        /* Creo un ArrayList donde almacenar los datos de las asignaturas */
        ArrayList<Asignatura> asi1 = new ArrayList();
        /* Creo todos los profesores con sus asignaturas */
        asi1.add(new Asignatura(2, "Bases de datos", 1, 105));
        asi1.add(new Asignatura(3, "Programacion", 1, 135));
        asi1.add(new Asignatura(4, "Lenguajes de Marcas", 1, 70));
        asi1.add(new Asignatura(6, "Acceso a datos", 2, 80));
        baseDatos.store(new Profesor("Diego", "Serrano Toca", "11111111-A", "987654321", 4, asi1));
        /* Limpio el ArrayList de las asignaturas antes de cargar las nuevas para el siguiente profesor */
        ArrayList<Asignatura> asi2 = new ArrayList();
        asi2.add(new Asignatura(1, "Sistemas informaticos", 2, 100));
        asi2.add(new Asignatura(2, "Bases de datos", 1, 105));
        asi2.add(new Asignatura(3, "Programacion", 1, 135));
        asi2.add(new Asignatura(5, "Entornos", 1, 50));
        baseDatos.store(new Profesor("David", "Salas Torre", "22222222-B", "887654321", 4, asi2));
        ArrayList<Asignatura> asi3 = new ArrayList();
        asi3.add(new Asignatura(9, "Empresa e iniciativa emprendedora", 2, 35));
        baseDatos.store(new Profesor("Marta", "Ugarte Uriarte", "33333333-C", "787654321", 1, asi3));
        ArrayList<Asignatura> asi4 = new ArrayList();
        asi4.add(new Asignatura(5, "Entornos", 1, 50));
        asi4.add(new Asignatura(6, "Acceso a datos", 2, 80));
        asi4.add(new Asignatura(7, "Programacion multimedia", 2, 55));
        asi4.add(new Asignatura(8, "Programacion de servicios", 2, 40));
        baseDatos.store(new Profesor("Cristina", "Arranz Salmon", "44444444-D", "687654321", 4, asi4));
        ArrayList<Asignatura> asi5 = new ArrayList();
        asi5.add(new Asignatura(3, "Programacion", 1, 135));
        asi5.add(new Asignatura(6, "Acceso a datos", 2, 80));
        asi5.add(new Asignatura(7, "Programacion multimedia", 2, 55));
        baseDatos.store(new Profesor("Fernando", "Pereda Mingo", "55555555-E", "587654321", 3, asi5));
        /* Grabo todos los datos en la BD */
        baseDatos.commit();
        
        /* Apartado 1 */
        System.out.println("Visualiza todos los profesores cuyo nombre no empice por 'D' ni acabe en 'a'. (Apartado 1)");
        visualizaProfesores(baseDatos);
        /* Apartado 2 */
        System.out.println("Modifica los datos del profesor Salas Torre. (Apartado 2)");
        modificaProfesor(baseDatos);
        /* Apartado 3 */
        System.out.println("Borra todos los profesores con menos de 4 asignaturas. (Apartado 3)");
        borraProfesores(baseDatos);
        
        /* Cierro la base de datos antes de salir */
        baseDatos.close();
    }
    
    /* Metodo para visualizar los datos de un Profesor, se usará en el resto de los metodos */
    public static void visualizaDatos (Profesor pro){
        System.out.println("Nombre: " + pro.getNombre());
        System.out.println("Apellidos: " + pro.getApellidos());
        System.out.println("DNI: " + pro.getDni());
        System.out.println("Telefono: " + pro.getTelefono());
        System.out.println("Imparte: ");
        /* Recorro todas sus asinagturas para mostrarlas */
        for(Asignatura asi : pro.getAsignaturas()){
            System.out.println("    Asignatura: " + asi.getNombre());
            System.out.println("    Curso: " + asi.getCurso());
            System.out.println("    Horas: " + asi.getHoras() + "\n");
        }
        System.out.println("");
    }
    
    /* Metodo que visualiza todos los Profesores cuyo nombre no empiece por "D" ni acabe en "a" (Apartado 1) */
    public static void visualizaProfesores(ObjectContainer bd){
        /* Lo primero creo un objeto Query para poder hacer la consulta */
        Query con = bd.query();
        /* A continuación, aplicamos los parametros de la consulta */
        /* Le indico la clase con la que vamos a trabajar */
        con.constrain(Profesor.class);
        /* Le indico el atributo que queremos comparar y el valor de comparación */
        /* Que el nombre no empiece por "D" */
        con.descend("nombre").constrain("D").startsWith(true).not();
        /* Que el nombre no acabe por "a" */
        con.descend("nombre").constrain("a").endsWith(true).not();
        /* Ejecuto la consulta y almaceno el resultado de la misma en un ObjectSet */
        ObjectSet res = con.execute();
        /* Recorro el resultado de la consulta y voy pintando los datos mientras haya datos */
        while (res.hasNext()){
            Profesor pro = (Profesor) res.next();
            /* Llamo al metodo que nos muestra la información */
            visualizaDatos(pro);
        }
    }
    
    /* Metodo que modifica el nombre y apellidos del Profesor con dni 22222222-B por mi nombre y apellidos (Apartado 2)*/
    public static void modificaProfesor(ObjectContainer bd){
        /* Extraigo el objeto Profesor que tiene el dni 22222222-B mediante una consulta por ejemplo */
        ObjectSet res = bd.queryByExample(new Profesor(null, null, "22222222-B", null, 0, null));
        /* Hago el next() directamente por que solo hay uno con ese dni */
        Profesor pro = (Profesor) res.next();
        System.out.println("Datos del Profesor con dni 22222222-B antes de modificarlos.");
        /* Muestro los datos antes de realizar la modificación */
        visualizaDatos(pro);
        /* Modifico el registro, poniendo mi nombre y apellidos */
        pro.setNombre("Mi nombre");
        pro.setApellidos("Mis apellidos");
        /* Lo guardo en la base de datos */
        bd.store(pro);
        bd.commit();
        /* Vuelvo a cargar el objeto desde la base de datos para confirmarlo */
        res = bd.queryByExample(new Profesor(null, null, "22222222-B", null, 0, null));
        pro = (Profesor) res.next();
        System.out.println("Datos del Profesor con dni 22222222-B despues de modificarlos.");
        /* Muestro los datos antes de realizar la modificación */
        visualizaDatos(pro);
    }
    
    /* Metodo que borra todos los Profesores que imparten menos de 4 asignaturas (Apartado 3) */
    public static void borraProfesores(ObjectContainer bd){
        /* Lo primero creo un objeto Query para poder hacer la consulta */
        Query con = bd.query();
        /* A continuación, aplicamos los parametros de la consulta */
        /* Le indico la clase con la que vamos a trabajar */
        con.constrain(Profesor.class);
        /* Le indico el atributo que queremos comparar y el valor de comparación, en este caso numAsignaturas menor que 4 */
        con.descend("numAsignaturas").constrain(4).smaller();
        /* Ejecuto la consulta y almaceno el resultado de la misma en un ObjectSet */
        ObjectSet res = con.execute();
        /* Recorro el resultado de la consulta y voy pintando los datos mientras haya datos */
        while (res.hasNext()){
            Profesor pro = (Profesor) res.next();
            /* Llamo al metodo que nos muestra la información */
            bd.delete(pro);
        }
        /* Muestro todos los profesores que quedan */
        res = bd.queryByExample(new Profesor(null, null, null, null, 0, null));
        while (res.hasNext()){
            Profesor pro = (Profesor) res.next();
            visualizaDatos(pro);
        }
    }
    
}

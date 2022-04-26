/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaquetePrincipal;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet; //consultas
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;//consultas SODA
import java.io.File;

/**
 *
 * @author Antonio
 */
public class Db4o {

    //Declaramos un Objectainer para operar con la base de objetos
    private ObjectContainer baseDatos = startDb40();

    public ObjectContainer startDb40() {

        File fichero = new File("BDJefeHijo");//Se borran los que habia antes insertados
        fichero.delete();
        //Conexión, apertura o creación de la base de objetos db4o
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Jefe.class).cascadeOnDelete(true);
        //Realizar un try catch para las operaciones de consulta,borrado,inserto y modificaciones
        try {
            if (baseDatos == null) {
                //Abrimos una conexión con la base de datos, si no está creada se creará
                ObjectContainer baseDatos = Db4oEmbedded.openFile("BDJefeHijo ");

                baseDatos.store(new Jefe("Ángel", 5, 53, new Hijo("Gustavo", 7)));
                baseDatos.store(new Jefe("Nieves", 3, 45, new Hijo("Iván", 3)));
                baseDatos.store(new Jefe("Jesús", 3, 5, new Hijo("Noelia", 3)));
                baseDatos.store(new Jefe("Dolores", 5, 63, new Hijo("Sergio", 7)));
                baseDatos.store(new Jefe("Vicki", 3, 5, null));
                baseDatos.store(new Jefe("Fátima", 5, 63, new Hijo("Lidia", 27)));
                baseDatos.store(new Jefe("Juan Luís", 3, 5, null));
                baseDatos.store(new Jefe("Elena", 1, 42, new Hijo("David", 19)));
                baseDatos.store(new Jefe("Miguel", 20, 45, new Hijo("Paula", 3)));
                baseDatos.store(new Jefe("Jesús", 19, 44, new Hijo("Rubén", 12)));
                baseDatos.query();

                return baseDatos;
            }
        } catch (Exception e) {
            if (baseDatos != null) {
                baseDatos.close(); // cerrar la base de datos antes de salir
            }
        }
        return this.baseDatos;
    }

    /*Método para mostrar objetos recuperados de la Base de Objetos, he utilizado el
    QBE que para este tipo de consultas fáciles e slo mejor, pero igual en otras
    no es suficiente o simplemente no pueden ser expresadas fácilmente*/
    public void mostrarConsulta(ObjectSet resul) {//se le pasa un objeto como parametro
        //Con esto recuperamos el numero de objetos que hay 
        System.out.println("Recuperados " + resul.size() + " Objetos\n");
        while (resul.hasNext()) {//nos devuelve los que hay con el iterador hasNext
            System.out.println(resul.next());
        }
    }
    //metodo para  consultar jefes mayores de 55
    public void jefesMayores55() {
        //declara un objeto Query para consultar la base de datos
        Query query = baseDatos.query();
        query.constrain(Jefe.class);//le decimos donde queremos borrar
        query.descend("edad").constrain(55).greater();//restringimos a mas de 55 años
        ObjectSet result = query.execute();
        mostrarConsulta(result);//muestro la consulta con el metodo mostrarConsulta
    }

    /* Méteodo que modifica la edad del jefe cuyo nombre es  Miguel por ejemplo
    y almacena en la base de objetos los nuevos valores*/
    public void actualizarEdad(String nombre) {//
        //se consulta a la base de datos por el jefe del nombre indicado
        ObjectSet res = baseDatos.queryByExample(new Jefe(nombre, null, null, null));
        Jefe jefe = (Jefe) res.next();//se obtiene el objeto consultado en la clase Jefes
        //Saco los datos del Jefe 
        System.out.println("Datos de: " + nombre + "\n" + jefe);
        jefe.setEdad(jefe.getEdad() + 1);// cambiamos la edad a +1
        baseDatos.store(jefe);//se almacena el nuevo objeto jefe
        //y ahora le decimos como es su nueva edad
        res = baseDatos.queryByExample(new Jefe(nombre, null, null, null));
        jefe = (Jefe) res.next();//obtenemos el nuevo objeto jefe actualizado
        System.out.println("La nueva edad de: " + jefe.getNombre() + " es " + jefe.getEdad() + " Años");
    }

    /*Método que elimina de la base de objetos [con delete()]los jefes con mas
    de 6 ños en la empresa, este se podría hacer igual que el metodo mostrarconsulta pasandole
    como parametro int edad,  */
    public void borrarJefesAntiguos() {
        Query query = baseDatos.query();
        query.constrain(Jefe.class);//le decimos donde queremos borrar
        query.descend("anioEmpresa").constrain(6).greater();//restringimos a mas de 6 años en la empresa
        ObjectSet resul = query.execute();
        /*mientras que sean mayores de 6 años el iterador hasNext nos devuelve
        los que son mayores de 6 años en la empresa*/
        while (resul.hasNext()) {
            Jefe jefe = (Jefe) resul.next();// se obtiene el objeto consultado en Jefe           
            System.out.println("Borrando a: " + jefe);
            baseDatos.delete(jefe);// se elimina el objeto jefe de la base de objetos 
        }
    }

    public void buscarTodos() {
        Query query = baseDatos.query();//
        query.constrain(Jefe.class);
        ObjectSet result = query.execute();
        mostrarConsulta(result);//muesstro la consulta con el metodo que hice QBE
    }

    public void cerrarConexion() {//cierro todo
        baseDatos.commit();
        baseDatos.close();
    }
}

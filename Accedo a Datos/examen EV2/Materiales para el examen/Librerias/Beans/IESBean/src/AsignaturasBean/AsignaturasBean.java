/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AsignaturasBean;

/* Voy añadiendo los import que necesito */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DIEGO
 */
public class AsignaturasBean implements Serializable {
    
    public AsignaturasBean() {}

    protected int id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    protected String nombre;

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    protected int curso;

    /**
     * Get the value of curso
     *
     * @return the value of curso
     */
    public int getCurso() {
        return curso;
    }

    /**
     * Set the value of curso
     *
     * @param curso new value of curso
     */
    public void setCurso(int curso) {
        this.curso = curso;
    }

    protected int horas;

    /**
     * Get the value of horas
     *
     * @return the value of horas
     */
    public int getHoras() {
        return horas;
    }

    /**
     * Set the value of horas
     *
     * @param horas new value of horas
     */
    public void setHoras(int horas) {
        this.horas = horas;
    }

    /* Creo una clase auxiliar para manejar cada asignatura */
    private class Asignatura{
        int id;
        String nombre;
        int curso;
        int horas;
        
        /*Creo un constructor por defecto vacio */
        public Asignatura(){}
        
        /* Creo el constructor con todos los parametros */
        public Asignatura(int id, String nom, int cur, int hor){
            this.id = id;
            this.nombre = nom;
            this.curso = cur;
            this.horas = hor;
        }
    }
    
    /* Creo un vector privado donde cargar todos los datos de la BD */
    private Vector asignaturas = new Vector();
    
    /* Creo un metodo que me diga el número de matriculas cargadas en el vector */
    public int numAsignaturas(){
        return asignaturas.size();
    }
    
    /* Creo el metodo que me permita cargar todos los datos de la BD en el vector */
    public void recargarDatos(){
        /* Lo primero dejo el vector vacio, si es que no lo está ya */
        if(!asignaturas.isEmpty()) asignaturas.removeAllElements();
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            Statement estado = con.createStatement();
            ResultSet rs = estado.executeQuery("select * from asignaturas");
            /* Recorro el resultado de la consulta y voy añadiendo todos los registros al vector */
            while(rs.next()){
                Asignatura asi = new Asignatura(rs.getInt(1),
                                              rs.getString(2),
                                              rs.getInt(3),
                                              rs.getInt(4));
                asignaturas.add(asi);
            }
            /* Una vez cargados todos los datos en el vector, asignamos a los atributos
               el valor de los datos del primer registro */
            Asignatura asi = (Asignatura) asignaturas.get(0);
            this.id = asi.id;
            this.nombre = asi.nombre;
            this.curso = asi.curso;
            this.horas = asi.horas;
            /* Por último cierro todas las partes de la conexion con la BD */
            rs.close();
            estado.close();
            con.close();
        } catch (SQLException e){
            /* Si se lanza alguna excepcion limpio los valores de los atributos */
            this.id = 0;
            this.nombre = "";
            this.curso = 0;
            this.horas = 0;
            Logger.getLogger(AsignaturasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /* Creo el metodo que me permita seleccionar un registro concreto */
    public void seleccionarFila(int i){
        /* Lo primero es comprobar que el indice este dentro del rango
           si está fuera dejo los campos en blanco */
        if(i < numAsignaturas()){
            /* Si lo esta cargo los datos */
            Asignatura asi = (Asignatura) asignaturas.get(i);
            this.id = asi.id;
            this.nombre = asi.nombre;
            this.curso = asi.curso;
            this.horas = asi.horas;
        } else {
            /* Sino lo esta limpio los atributos */
            this.id = 0;
            this.nombre = "";
            this.curso = 0;
            this.horas = 0;
        }
    }
    
    /* Creo el metodo que me permite obtener los datos de los registros que coincide con un id concreto */
    public void recargarID(int id){
        /* Lo primero dejo el vector vacio, si es que no lo está ya */
        if(!asignaturas.isEmpty()) asignaturas.removeAllElements();
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            PreparedStatement estado = con.prepareStatement("select * from asignaturas where id = ?");
            estado.setInt(1, id);
            ResultSet rs = estado.executeQuery();
            /* Recorro el resultado de la consulta y voy añadiendo todos los registros al vector */
            while(rs.next()){
                Asignatura asi = new Asignatura(rs.getInt(1),
                                              rs.getString(2),
                                              rs.getInt(3),
                                              rs.getInt(4));
                asignaturas.add(asi);
            }
            /* Una vez cargados todos los datos en el vector, asignamos a los atributos
               el valor de los datos del primer registro */
            Asignatura asi = (Asignatura) asignaturas.get(0);
            this.id = asi.id;
            this.nombre = asi.nombre;
            this.curso = asi.curso;
            this.horas = asi.horas;
            /* Por último cierro todas las partes de la conexion con la BD */
            rs.close();
            estado.close();
            con.close();
        } catch (SQLException e){
            /* Si se lanza alguna excepcion limpio los valores de los atributos */
            this.id = 0;
            this.nombre = "";
            this.curso = 0;
            this.horas = 0;
            Logger.getLogger(AsignaturasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
     /* Creo el metodo que nos permite añadir una nueva asigantura a la BD */
    public void anadeAsignatura(){
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            /* Preparo la sentencia para insertar los datos */
            PreparedStatement estado = con.prepareStatement("insert into asignaturas values (?,?,?,?)");
            /* Cargo los datos */
            estado.setInt(1, id);
            estado.setString(2, nombre);
            estado.setInt(3, curso);
            estado.setInt(4, horas);
            /* Ejecuto la sentencia */
            estado.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(AsignaturasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /* Creo el metodo que nos permite borrar una asignatura de la BD */
    public void borraAsignatura(){
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            /* Preparo la sentencia para borrar los datos */
            PreparedStatement estado = con.prepareStatement("delete from asignaturas where id = ?");
            /* Cargo los datos */
            estado.setInt(1, id);
            /* Ejecuto la sentencia */
            estado.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(AsignaturasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /* Creo el metodo que nos permite modificar una asignatura de la BD */
    public void modificaAsignatura(int hor){
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            /* Preparo la sentencia para modificar los datos */
            PreparedStatement estado = con.prepareStatement("update asignaturas set horas = ? where id = ?");
            /* Cargo los datos */
            estado.setInt(1, hor);
            estado.setInt(2, id);
            /* Ejecuto la sentencia */
            estado.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(AsignaturasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}

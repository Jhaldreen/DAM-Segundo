/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfesoresBean;

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
public class ProfesoresBean implements Serializable {
     
    public ProfesoresBean() {}
    
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

    protected String apellidos;

    /**
     * Get the value of apellidos
     *
     * @return the value of apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Set the value of apellidos
     *
     * @param apellidos new value of apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    protected String dni;

    /**
     * Get the value of dni
     *
     * @return the value of dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Set the value of dni
     *
     * @param dni new value of dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    protected String telefono;

    /**
     * Get the value of telefono
     *
     * @return the value of telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Set the value of telefono
     *
     * @param telefono new value of telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    protected int numAsignaturas;

    /**
     * Get the value of numAsignaturas
     *
     * @return the value of numAsignaturas
     */
    public int getNumAsignaturas() {
        return numAsignaturas;
    }

    /**
     * Set the value of numAsignaturas
     *
     * @param numAsignaturas new value of numAsignaturas
     */
    public void setNumAsignaturas(int numAsignaturas) {
        this.numAsignaturas = numAsignaturas;
    }
    
    /* Creo una clase auxiliar para manejar cada profesor */
    private class Profesor{
        String nombre;
        String apellidos;
        String dni;
        String telefono;
        int numAsignaturas;
        
        /*Creo un constructor por defecto vacio */
        public Profesor(){}
        
        /* Creo el constructor con todos los parametros */
        public Profesor(String nom, String ape, String dni, String tel, int nAs){
            this.nombre = nom;
            this.apellidos = ape;
            this.dni = dni;
            this.telefono = tel;
            this.numAsignaturas = nAs;
        }
    }
    
    /* Creo un vector privado donde cargar todos los datos de la BD */
    private Vector profesores = new Vector();
    
    /* Creo un metodo que me diga el número de profesores cargados en el vector */
    public int numProfesores(){
        return profesores.size();
    }
    
    /* Creo el metodo que me permita cargar todos los datos de la BD en el vector */
    public void recargarDatos(){
        /* Lo primero dejo el vector vacio, si es que no lo está ya */
        if(!profesores.isEmpty()) profesores.removeAllElements();
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            Statement estado = con.createStatement();
            ResultSet rs = estado.executeQuery("select * from profesores");
            /* Recorro el resultado de la consulta y voy añadiendo todos los registros al vector */
            while(rs.next()){
                Profesor pro = new Profesor(rs.getString(1),
                                              rs.getString(2),
                                              rs.getString(3),
                                              rs.getString(4),
                                              rs.getInt(5));
                profesores.add(pro);
            }
            /* Una vez cargados todos los datos en el vector, asignamos a los atributos
               el valor de los datos del primer registro */
            Profesor pro = (Profesor) profesores.get(0);
            this.nombre = pro.nombre;
            this.apellidos = pro.apellidos;
            this.dni = pro.dni;
            this.telefono = pro.telefono;
            this.numAsignaturas = pro.numAsignaturas;
            /* Por último cierro todas las partes de la conexion con la BD */
            rs.close();
            estado.close();
            con.close();
        } catch (SQLException e){
            /* Si se lanza alguna excepcion limpio los valores de los atributos */
            this.nombre = "";
            this.apellidos = "";
            this.dni = "";
            this.telefono = "";
            this.numAsignaturas = 0;
            Logger.getLogger(ProfesoresBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /* Creo el metodo que me permita seleccionar un registro concreto */
    public void seleccionarFila(int i){
        /* Lo primero es comprobar que el indice este dentro del rango
           si está fuera dejo los campos en blanco */
        if(i < numProfesores()){
            /* Si lo esta cargo los datos */
            Profesor pro = (Profesor) profesores.get(i);
            this.nombre = pro.nombre;
            this.apellidos = pro.apellidos;
            this.dni = pro.dni;
            this.telefono = pro.telefono;
            this.numAsignaturas = pro.numAsignaturas;
        } else {
            /* Sino lo esta limpio los atributos */
            this.nombre = "";
            this.apellidos = "";
            this.dni = "";
            this.telefono = "";
            this.numAsignaturas = 0;
        }
    }
    
    /* Creo el metodo que me permite obtener los datos de los registros que coincide con un dni concreto */
    public void recargarDNI(String dni){
        /* Lo primero dejo el vector vacio, si es que no lo está ya */
        if(!profesores.isEmpty()) profesores.removeAllElements();
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            PreparedStatement estado = con.prepareStatement("select * from profesores where dni = ?");
            estado.setString(1, dni);
            ResultSet rs = estado.executeQuery();
            /* Recorro el resultado de la consulta y voy añadiendo todos los registros al vector */
            while(rs.next()){
                Profesor pro = new Profesor(rs.getString(1),
                                              rs.getString(2),
                                              rs.getString(3),
                                              rs.getString(4),
                                              rs.getInt(5));
                profesores.add(pro);
            }
            /* Una vez cargados todos los datos en el vector, asignamos a los atributos
               el valor de los datos del primer registro */
            Profesor pro = (Profesor) profesores.get(0);
            this.nombre = pro.nombre;
            this.apellidos = pro.apellidos;
            this.dni = pro.dni;
            this.telefono = pro.telefono;
            this.numAsignaturas = pro.numAsignaturas;
            /* Por último cierro todas las partes de la conexion con la BD */
            rs.close();
            estado.close();
            con.close();
        } catch (SQLException e){
            /* Si se lanza alguna excepcion limpio los valores de los atributos */
            this.nombre = "";
            this.apellidos = "";
            this.dni = "";
            this.telefono = "";
            this.numAsignaturas = 0;
            Logger.getLogger(ProfesoresBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /* Creo el metodo que nos permite añadir un nuevo profesor a la BD */
    public void anadeProfesor(){
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            /* Preparo la sentencia para insertar los datos */
            PreparedStatement estado = con.prepareStatement("insert into profesores values (?,?,?,?,?)");
            /* Cargo los datos */
            estado.setString(1, nombre);
            estado.setString(2, apellidos);
            estado.setString(3, dni);
            estado.setString(4, telefono);
            estado.setInt(5, numAsignaturas);
            /* Ejecuto la sentencia */
            estado.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(ProfesoresBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /* Creo el metodo que nos permite borrar un profesor de la BD */
    public void borraProfesor(){
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            /* Preparo la sentencia para borrar los datos */
            PreparedStatement estado = con.prepareStatement("delete from profesores where dni = ?");
            /* Cargo los datos */
            estado.setString(1, dni);
            /* Ejecuto la sentencia */
            estado.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(ProfesoresBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /* Creo el metodo que nos permite modificar un profesor de la BD */
    public void modificaProfesor(String nom, String ape){
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            /* Preparo la sentencia para modificar los datos */
            PreparedStatement estado = con.prepareStatement("update profesores set nombre = ?, apellidos = ? where dni = ?");
            /* Cargo los datos */
            estado.setString(1, nom);
            estado.setString(2, ape);
            estado.setString(3, dni);
            /* Ejecuto la sentencia */
            estado.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(ProfesoresBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}

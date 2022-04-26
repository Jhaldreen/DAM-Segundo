/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatriculasBean;

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
public class MatriculasBean implements Serializable {
    
    public MatriculasBean() {}

    protected String profesor;

    /**
     * Get the value of profesor
     *
     * @return the value of profesor
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     * Set the value of profesor
     *
     * @param profesor new value of profesor
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    protected int asignatura;

    /**
     * Get the value of asignatura
     *
     * @return the value of asignatura
     */
    public int getAsignatura() {
        return asignatura;
    }

    /**
     * Set the value of asignatura
     *
     * @param asignatura new value of asignatura
     */
    public void setAsignatura(int asignatura) {
        this.asignatura = asignatura;
    }

    /* Creo una clase auxiliar para manejar cada matricula */
    private class Matricula{
        String profesor;
        int asignatura;
        
        /*Creo un constructor por defecto vacio */
        public Matricula(){}
        
        /* Creo el constructor con todos los parametros */
        public Matricula(String pro, int asi){
            this.profesor = pro;
            this.asignatura = asi;
        }
    }
    
    /* Creo un vector privado donde cargar todos los datos de la BD */
    private Vector matriculas = new Vector();
    
    /* Creo un metodo que me diga el número de matriculas cargadas en el vector */
    public int numMatriculas(){
        return matriculas.size();
    }
    
    /* Creo el metodo que me permita cargar todos los datos de la BD en el vector */
    public void recargarDatos(){
        /* Lo primero dejo el vector vacio, si es que no lo está ya */
        if(!matriculas.isEmpty()) matriculas.removeAllElements();
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            Statement estado = con.createStatement();
            ResultSet rs = estado.executeQuery("select * from matriculas");
            /* Recorro el resultado de la consulta y voy añadiendo todos los registros al vector */
            while(rs.next()){
                Matricula mat = new Matricula(rs.getString(1),
                                              rs.getInt(2));
                matriculas.add(mat);
            }
            /* Una vez cargados todos los datos en el vector, asignamos a los atributos
               el valor de los datos del primer registro */
            Matricula mat = (Matricula) matriculas.get(0);
            this.profesor = mat.profesor;
            this.asignatura = mat.asignatura;
            /* Por último cierro todas las partes de la conexion con la BD */
            rs.close();
            estado.close();
            con.close();
        } catch (SQLException e){
            /* Si se lanza alguna excepcion limpio los valores de los atributos */
            this.profesor = "";
            this.asignatura = 0;
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /* Creo el metodo que me permita seleccionar un registro concreto */
    public void seleccionarFila(int i){
        /* Lo primero es comprobar que el indice este dentro del rango
           si está fuera dejo los campos en blanco */
        if(i < numMatriculas()){
            /* Si lo esta cargo los datos */
            Matricula mat = (Matricula) matriculas.get(i);
            this.profesor = mat.profesor;
            this.asignatura = mat.asignatura;
        } else {
            /* Sino lo esta limpio los atributos */
            this.profesor = "";
            this.asignatura = 0;
        }
    }
    
    /* Creo el metodo que me permite obtener los datos de los registros que coincide con un profesor concreto */
    public void recargarProfesor(String pro){
        /* Lo primero dejo el vector vacio, si es que no lo está ya */
        if(!matriculas.isEmpty()) matriculas.removeAllElements();
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            PreparedStatement estado = con.prepareStatement("select * from matriculas where profesor = ?");
            estado.setString(1, pro);
            ResultSet rs = estado.executeQuery();
            /* Recorro el resultado de la consulta y voy añadiendo todos los registros al vector */
            while(rs.next()){
                Matricula mat = new Matricula(rs.getString(1),
                                              rs.getInt(2));
                matriculas.add(mat);
            }
            /* Una vez cargados todos los datos en el vector, asignamos a los atributos
               el valor de los datos del primer registro */
            Matricula mat = (Matricula) matriculas.get(0);
            this.profesor = mat.profesor;
            this.asignatura = mat.asignatura;
            /* Por último cierro todas las partes de la conexion con la BD */
            rs.close();
            estado.close();
            con.close();
        } catch (SQLException e){
            /* Si se lanza alguna excepcion limpio los valores de los atributos */
            this.profesor = "";
            this.asignatura = 0;
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /* Creo el metodo que me permite obtener los datos de los registros que coincide con una asignatura concreta */
    public void recargarAsignatura(int asi){
        /* Lo primero dejo el vector vacio, si es que no lo está ya */
        if(!matriculas.isEmpty()) matriculas.removeAllElements();
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            PreparedStatement estado = con.prepareStatement("select * from matriculas where asigantura = ?");
            estado.setInt(1, asi);
            ResultSet rs = estado.executeQuery();
            /* Recorro el resultado de la consulta y voy añadiendo todos los registros al vector */
            while(rs.next()){
                Matricula mat = new Matricula(rs.getString(1),
                                              rs.getInt(2));
                matriculas.add(mat);
            }
            /* Una vez cargados todos los datos en el vector, asignamos a los atributos
               el valor de los datos del primer registro */
            Matricula mat = (Matricula) matriculas.get(0);
            this.profesor = mat.profesor;
            this.asignatura = mat.asignatura;
            /* Por último cierro todas las partes de la conexion con la BD */
            rs.close();
            estado.close();
            con.close();
        } catch (SQLException e){
            /* Si se lanza alguna excepcion limpio los valores de los atributos */
            this.profesor = "";
            this.asignatura = 0;
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
     /* Creo el metodo que nos permite añadir una nueva matricula a la BD */
    public void anadeMatricula(){
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            /* Preparo la sentencia para insertar los datos */
            PreparedStatement estado = con.prepareStatement("insert into matriculas values (?,?)");
            /* Cargo los datos */
            estado.setString(1, profesor);
            estado.setInt(2, asignatura);
            /* Ejecuto la sentencia */
            estado.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /* Creo el metodo que nos permite borrar una matricula de la BD */
    public void borraMatricula(){
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            /* Preparo la sentencia para borrar los datos */
            PreparedStatement estado = con.prepareStatement("delete from matriculas where profesor = ? and asignatura = ?");
            /* Cargo los datos */
            estado.setString(1, profesor);
            estado.setInt(2, asignatura);
            /* Ejecuto la sentencia */
            estado.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /* Creo el metodo que nos permite modificar una matricula de la BD */
    public void modificaMatricula(String pro){
        try{
            /* Ahora voy a establecer la conexion con el servidor de la BD */
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ies", "root", "root");
            /* Preparo la sentencia para modificar los datos */
            PreparedStatement estado = con.prepareStatement("update matriculas set profesor = ? where profesor = ? and asignatura = ?");
            /* Cargo los datos */
            estado.setString(1, pro);
            estado.setString(2, profesor);
            estado.setInt(3, asignatura);
            /* Ejecuto la sentencia */
            estado.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}

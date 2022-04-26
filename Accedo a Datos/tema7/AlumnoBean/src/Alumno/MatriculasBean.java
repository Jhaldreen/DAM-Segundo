/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alumno;

import java.sql.Statement;
import java.beans.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
//Eliminamos lo que no hace falta al crear la clase JavaBeanComponents
public class MatriculasBean implements Serializable {

    private PropertyChangeSupport propertySupport;

    public MatriculasBean() {
        propertySupport = new PropertyChangeSupport(this);
        try {
            //Para cargar las filas
            this.recargarFilas();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //con el boton derecho le damos a ADDProperty, y añadimos los campos de la BD
    //o escribimos las variables y añadimos sus getter y setters 
    /********************** DNI************************************************/
    protected String DNI;

    /**
     * Get the value of DNI
     *
     * @return the value of DNI
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * Set the value of DNI
     *
     * @param DNI new value of DNI
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    
   /********************** Nombre del Modulo **********************************/
    protected String NombreModulo;

    /**
     * Get the value of NombreModulo
     *
     * @return the value of NombreModulo
     */
    public String getNombreModulo() {
        return NombreModulo;
    }

    /**
     * Set the value of NombreModulo
     *
     * @param NombreModulo new value of NombreModulo
     */
    public void setNombreModulo(String NombreModulo) {
        this.NombreModulo = NombreModulo;
    }
    
   /********************** Nombre del curso ***********************************/
    protected String Curso;

    /**
     * Get the value of curso
     *
     * @return the value of curso
     */
    public String getCurso() {
        return Curso;
    }

    /**
     * Set the value of curso
     *
     * @param curso new value of curso
     */
    public void setCurso(String Curso) {
        this.Curso = Curso;
    }
    /********** Nota **********************************************************/
    protected double Nota;

    /**
     * Get the value of Nota
     *
     * @return the value of Nota
     */
    public double getNota() {
        return Nota;
    }

    /**
     * Set the value of Nota
     *
     * @param Nota new value of Nota
     */
    public void setNota(double Nota) {
        this.Nota = Nota;
    }

    /**
     * **********************************************************************
     * Definimos los métodos y atributos privados del componente que usaremos
     * para darle funcionalidad. Clase auxiliar que usaremos para crear un
     * vector privado de alumnos. Buscamos las matrículas de base de datos para
     * rellenar un Vector interno de matrículas Para ello necesitamos una clase
     * Matrícula donde poder almacenar los datos
     */
    private class Matricula {

        String DNI;
        String NombreModulo;
        String Curso;
        double Nota;

        public Matricula() {
        }

        public Matricula(String nDNI, String nNombreModulo, String nCurso, double nNota) {
            this.DNI = nDNI;
            this.NombreModulo = nNombreModulo;
            this.Curso = nCurso;
            this.Nota = nNota;
        }

    }
    /**
     * ****************************************************
     * Usaremos un vector auxiliar para cargar la información de la tabla de
     * forma que tengamos acceso a los datos sin necesidad de estar conectados
     * constantemente
     */
    /**
     * *************** VECTOR ************************************************
     */
    private Vector<Matricula> listadoMatriculas = new Vector();

    /**
     * ***********************************************************************
     * Actualiza el contenido de la tabla en el vector de alumnos Las
     * propiedades contienen el valor del primer elementos de la tabla Dentro
     * del método recargarFilas hacemos una consulta y rellenamos el Vector
     * listadoMatrícula con datos
     */
    private void recargarFilas() throws ClassNotFoundException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/alumnos", "root", "");
            Statement s = con.createStatement();
            //Importante mirar bien el nombre de la tabla que se va a ejecutar
            ResultSet rs = s.executeQuery("select * from matriculas");
            while (rs.next()) {
                Matricula a = new Matricula(rs.getString("DNI"),
                        rs.getString("NombreModulo"),
                        rs.getString("Curso"),
                        rs.getDouble("Nota"));

                listadoMatriculas.add(a);
            }
            //instanciamos la clase matricula
            Matricula a = new Matricula();
            a = (Matricula) listadoMatriculas.elementAt(0);//*****arrays empiezan en 0 OJITO
            this.DNI = a.DNI;
            this.NombreModulo = a.NombreModulo;
            this.Curso = a.Curso;
            this.Nota = a.Nota;
            rs.close();//cierro conexion BD
            con.close();//cierro conexion
        } catch (SQLException e) {

            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * ******************************************************
     *
     * @param i numero de la fila a cargar en las propiedades del componente
     *
     * Metodo para ver los datos de cada fila del vector
     */
    public void seleccionarFila(int i) {
        //no puede ser <= las matriculas no salen
        if (i < listadoMatriculas.size()) {
            Matricula a = new Matricula();//instanciamos la clase matricula
            a = (Matricula) listadoMatriculas.elementAt(i);//array con el vector
            this.DNI = a.DNI;
            this.NombreModulo = a.NombreModulo;
            this.Curso = a.Curso;
            this.Nota = a.Nota;

        } else {

            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = Nota;
        }
    }

    /**
     * Este método recibe el dni que queremos encontrar en nuestro vector. Como
     * el DNI es clave primaria como mucho encontrará 1 matrícula y en el caso
     * de que no encuentre ninguna mandará un mensaje notificándolo que no
     * existe ninguna matrícula con ese DNI.
     */
    public void recargarDNI(String dni) {

        Matricula a = new Matricula();
        int e = 0;
        for (int i = 0; i < this.listadoMatriculas.size(); i++) {
            a = listadoMatriculas.elementAt(i);
            if (a.DNI.equals(dni)) {
                this.DNI = a.DNI;
                this.NombreModulo = a.NombreModulo;
                this.Curso = a.Curso;
                this.Nota = a.Nota;

                e++;
            }

        }
        receptor.capturarBDModificada(new BDModificadaEvent(this));
        if (e == 0) {

            System.out.println("No se ha encontrado matriculas");
        }

    }
    /**
     * *******************************************************************
     * Código para añadir un nuevo alumno a la base de datos. cada vez que se
     * modifca el estado de la BD se genera un evento para que se recargue el
     * componente.
     */

    private BDModificadaListener receptor;

    public class BDModificadaEvent extends java.util.EventObject {

        // constructor
        public BDModificadaEvent(Object source) {
            super(source);
        }
    }

    //Define la interfaz para el nuevo tipo de evento
    public interface BDModificadaListener extends EventListener {

        public void capturarBDModificada(BDModificadaEvent ev);
    }

    public void addBDModificadaListener(BDModificadaListener receptor) {
        this.receptor = receptor;
    }

    public void removeBDModificadaListener(BDModificadaListener receptor) {
        this.receptor = null;
    }

    /**
     * ************************************************************************
     * Método que añade una matricula a la base de datos añade un registro a la
     * base de datos formado a partir de los valores de las propiedades del
     * componente.
     *
     * Se presupone que se han usado los métodos set para configurar
     * adecuadamente las propiedades con los datos del nuevo registro.
     */
    public void addMatricula() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/alumnos", "root", "");
            PreparedStatement s = con.prepareStatement("insert into matriculas values (?,?,?,?)");

            s.setString(1, DNI);
            s.setString(2, NombreModulo);
            s.setString(3, Curso);
            s.setDouble(4, Nota);

            s.executeUpdate();
            recargarFilas();
            receptor.capturarBDModificada(new BDModificadaEvent(this));
        } catch (SQLException ex) {
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * *****************************************************
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}

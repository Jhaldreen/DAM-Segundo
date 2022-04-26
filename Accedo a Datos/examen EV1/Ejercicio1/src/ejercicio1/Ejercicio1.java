/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/* Voy añadiendo los import necesarios */
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author DIEGO
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Creo el objeto que va a controlar la conexion con la BD */
        Connection con = creaConexion();
        /* Creo el ResultSet que almacenará los datos de la consulta */
        ResultSet datos = null;
        /* Compruebo que la conexion no ha fallado antes de seguir */
        if (con != null){
            /* Modifico un registro de la BD */
            modificaMedico(con, 40);
            /* Hago la consulta sobre la BD */
            datos = consultaIngresos(con);
            /* Compruebo que la consulta no ha dado problemas, si esta bien creo el XML */
            if (datos != null){
                creaXML(datos);
            }
        }
        
        try {
            /* Si existe el ResultSet lo cierro antes de acabar el programa */
            if(datos != null) datos.close();
            /* Si existe la conexion la cierro antes de acabar el programa */
            if(con != null) con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /* Este metodo establece la conexion con la BD */
    public static Connection creaConexion(){
        /*Creo las variables para poder establecer la conexion con la BD */
        String url = "jdbc:mysql://localhost/hospital";
        String usuario = "alumnoddam";
        String password = "alumnoddam";
        try {
            /* Creo la conexion con la BD */
            Connection conexion = DriverManager.getConnection(url, usuario, password);
            return conexion;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /* Este metodo modifica un registro de medico recibiendo por parametro el codigo (Apartado 1) */
    public static void modificaMedico(Connection con, int cod){
        try {
            PreparedStatement modifica = con.prepareStatement("update medico set nombre = ?, apellidos = ?"
                                                              + " where codidentificacion = ?");
            modifica.setString(1, "Tu nombre");
            modifica.setString(2, "Tus apellidos");
            modifica.setInt(3, cod);
            int reg = modifica.executeUpdate();
            System.out.println("Se ha actualizado " + reg + " registro de la tabla medico.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /* Este metodo realiza la consulta sobre la BD (Apartado 2) */
    public static ResultSet consultaIngresos(Connection conexion){
        try {
            /* Creo el Statement para poder realizar la consulta */
            Statement consulta = conexion.createStatement();
            /* Creo el ResultSet para almacenar los datos recibidos en la consulta */
            ResultSet salida = consulta.executeQuery("select i.numingreso, p.nombre, p.apellidos, "
                    + "m.nombre, m.apellidos, i.ncama, i.descripcion "
                    + "from ingreso i inner join medico m on i.CodIdentificacion = m.CodIdentificacion "
                    + "inner join paciente p on i.NumHistorial = p.NumHistorial");
            return salida;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /* Este metodo crea el documento XML e inserta los datos obtenidos en la consulta previa (Apartado 3) */
    public static void creaXML(ResultSet datos){
        try {
            /* Creo el DOM */
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            /* Creo el elemento raiz */
            Document doc = db.newDocument();
            Element raiz = doc.createElement("ingresos");
            doc.appendChild(raiz);
            
            /* A continuación voy a ir recorriendo los datos y cargandolos en el XML */
            while(datos.next()){
                /* Creo cada elemento ingreso */
                Element ingreso = doc.createElement("ingreso");
                /* Introduzco el numero del ingreso como atributo */
                ingreso.setAttribute("numero", datos.getString(1));
                /* Se lo añado al elemento raiz */
                raiz.appendChild(ingreso);
                /* Creo el elemento que tiene el nombre y los apellidos del paciente ingresado */
                Element paciente = doc.createElement("paciente");
                paciente.setTextContent(datos.getString(3) + ", " + datos.getString(2));
                ingreso.appendChild(paciente);
                /* Creo el elemento que tiene el nombre y los apellidos de medico que lo atiende */
                /* El nombre como atributo */
                Element medico = doc.createElement("medico");
                medico.setAttribute("nombre", datos.getString(4));
                medico.setTextContent(datos.getString(5));
                ingreso.appendChild(medico);
                /* Creo el elemento que tiene el numero de cama en la que esta el paciente */
                Element cama = doc.createElement("Numero_cama");
                cama.setTextContent(datos.getString(6));
                ingreso.appendChild(cama);
                /* Creo el elemento que contiene la descripción del motivo del ingreso */
                Element descripcion = doc.createElement("descripcion");
                descripcion.setTextContent(datos.getString(7));
                ingreso.appendChild(descripcion);
            }
            
            /* Finalmente voy a crear el archivo y pasarle el DOM que acabo de crear */
            /* Lo primero es crear el transformer */
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer tra = tf.newTransformer();
            /* Despues cargo la fuente del DOM */
            DOMSource fuente = new DOMSource(doc);
            /* Creo la salida hacia el documento que quiero crear */
            StreamResult salida = new StreamResult(new File("hospital.xml"));
            /* Establezco que meta un intro despues de cada linea, para que el XML de salida se vea bien */
            tra.setOutputProperty(OutputKeys.INDENT, "yes");
            /* Y hago la transformación con los dos últimos elementos creados */
            tra.transform(fuente, salida);

            /* Aviso por consola que el archivo se ha creado correctamente */
            System.out.println("\nArchivo hospital.xml creado correctamente");
        } catch (ParserConfigurationException | SQLException | TransformerException e){
            System.out.println(e.getMessage());
        } 
    }
}

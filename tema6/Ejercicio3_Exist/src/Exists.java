
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

/**
 *
 * @author Antonio
 */
public class Exists {
    

/* creando este metodo para usar mas adelante y conectar en más sitios si
    se diera el caso*/
   private XQConnection createConnection() throws XQException {
       /*Para referenciar conexiones(sesiones) con un SGBD especifico
       toda conexion se logra forzosamente a través de un objeto XQDataSource
       en los apuntes viene al revés, me parece más sencillo de entender así*/
        XQConnection conexion = null;
        /*para obtener los objetos*/
        XQDataSource recurso = new ExistXQDataSource();
        
        recurso.setProperty("serverName", "localhost");
        recurso.setProperty("port", "8080");//ojo aqui 8080 depende cual estes usando
        /*Configurados puerto y servidor , creamos conexion*/
        conexion = recurso.getConnection("admin", "");//ojo contraseñas si se ponen
        return conexion;
    }
    public static void main(String[] args) throws XQException {
         Exists exist = new Exists();
        XQConnection connection = exist.createConnection();

        if (connection == null) {
            throw new IllegalArgumentException("Fallo al conectar con eXist. Los datos de conexión no son válidos");
        }
        //probar primero la consulta, y escribirla BIEN
        String consulta = "for $libro in collection(/ejercicios)"
                         + " /bib/libro/titulo return $libro";

        try {
            /* para la ejecucion inmediata de las sentencias*/
            XQExpression query = connection.createExpression();
            /* metodo executeQuery para las consultas */
            XQResultSequence result = query.executeQuery(consulta);

            System.out.println("\t---------LIBROS---------\n"
                    + "-------------------------------------------------------");
            /*utilizamos next() para permitir la iteracion hacia delante de un 
            documento XML */
            while (result.next()) {
                /* obtenemos el metodo getItem() y como nuestro objeto es un String
                he leido en la API que se puede usar AsString no veia otra solucion*/
                System.out.println(result.getItemAsString(null));
            }

        } catch (XQException e) {
            e.printStackTrace();
        }
    }
    
}

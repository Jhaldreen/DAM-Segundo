
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Antonio
 */
public class Ejercicio2 {

   public static void main(String[] args) throws IOException, JRException  {
          
                 try {
               
            Connection con; 
            String url = "jdbc:mysql://localhost/fabrica";//indica la direccion del servidor
            con = DriverManager.getConnection(url, "root","");
            
            String ruta = "facturas.jasper";
            
            Map parametros = new HashMap();//creamos un mapa
            parametros.put("ID_CLIENTE","1" );//Al tener un solo parámetro, este es el que añadimos
            //creamos un objeto JasperPrint
            JasperPrint print = JasperFillManager.fillReport(ruta, parametros, con);
            
            JasperExportManager.exportReportToPdfFile(print, "facturas.pdf");
            
            //Abre el pdf generado
            File path = new File("facturas1.pdf");
            Desktop.getDesktop().open(path);
            
        } catch (SQLException ex) {
                     System.out.println("errot "+ex);
        }
          
    }
}
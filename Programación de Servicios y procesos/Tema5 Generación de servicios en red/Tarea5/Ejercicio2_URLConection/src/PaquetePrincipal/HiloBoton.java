package PaquetePrincipal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

/**
 *
 * @author Antonio
 *
 */
public class HiloBoton extends Thread {

    // variables
    private JTextArea jTextAreaCaja;
    private final String cadenaURL;

    //creamos un constructor que alamcena las variables 
    public HiloBoton(String cadenaURL, JTextArea jTextAreaCaja) {

        this.jTextAreaCaja = jTextAreaCaja;
        this.cadenaURL = cadenaURL;
    }

    public void run() {

        //variables locales
        int leido, contentLength = 1000;//tamaño de 1000
        char[] bufChar;//variable par el text/html
        byte[] bufByte;//variable para el PDF
        String texto = this.jTextAreaCaja.getText();
        try {

            //crea el objeto URL
            URL url = new URL(cadenaURL);
            //obtiene una conexion al recurso URL
            URLConnection con = url.openConnection();//nos conectamos
            //se conecta pudientdo interactuar con parámetros
            con.connect();
            //obtine el tipo de contenido
            String contentType = con.getContentType();
            //obtiene la fecha de la última modificación
            Date fecha = new Date(con.getLastModified());
            //Obtine la longitud
            long longitud = con.getContentLengthLong();
            //con el append podemos  ver a continuación los mensajes que ya haya escritos.
            texto += ("\nTipo de contenido: " + contentType
                    + "\nFecha de modificacion: " + fecha
                    + "\nLongitud: " + longitud + "\n");

            if (contentType.equals("application/pdf")) {
                //muestra un cuadro de dialogo modal para generar el fichero de destino
                texto += ("entrando en pdf");
                File archivoElegido = ficheroDestino();//escribir un fichero
                //si el fichero generado correctamente distinto de null
                if (archivoElegido != null) {
                    //flujo de descarga desde la url
                    InputStream reader = url.openStream();
                    //flujo de escritura en el fichero
                    FileOutputStream writer = new FileOutputStream(archivoElegido);

                    //buffer intermedio ajustado al content-Length enviado por el servidor
                    bufByte = new byte[contentLength];
                    texto += ("\nDescargando el pdf en directorio elegido... ");
                    //mientras quedan bytes por leer en el buffer intermedio
                    while ((leido = reader.read(bufByte)) > 0) {
                        writer.write(bufByte, 0, leido);
                        texto += ("\n");
                    }
                    //cierra el flujo de escritura
                    writer.close();
                    texto += ("El pdf ha sido descargado correctamente");

                }
            }//si se trata de texto o contenido html
            else if (contentType.startsWith("text/html")) {
                //flujo para descarga el cuerpo del texto de la pagina html
                InputStream in = con.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(in));
                //Buffer intermedio de tamaño medio 
                bufChar = new char[512];//tamaño 512

                texto += ("\n Escribiendo el cuerpo del texto en la salida\n");

                //mmientras quedan caracteres por leer
                while ((leido = bf.read(bufChar)) > 0) {
                    //se escriben en la salida
                    texto += ("Cuerpo: \n" + new String(bufChar, 0, leido));
                }
                texto += ("Cuerpo de texto obtenido ");
            } else {
                texto += ("No sé que hacer con el tipo de contenido indicado");
            }
        } catch (MalformedURLException ex) {//si la direccion esta mal escrita
            texto += ("error pagina no encontrada " + ex + "\n");
        } catch (IOException ex) {//si el fichero no existe
            texto = ("error fichero no existe " + ex + "\n");
        } finally {
            //llamamos al metodo y enviamos los mensajes en jTextArea
            enviarjTextArea(texto);

        }

    }
    // metodo que devuelve a la interfaz gráfica jTextArea

    public JTextArea enviarjTextArea(String mensaje) {
        //meto los mensajes en textArea 
        jTextAreaCaja.setText(mensaje + "\n");

        return jTextAreaCaja;//devuelvo el textArea
    }

    /**
     * **************************************************************************
     * muestra un cuadro de diálogo para crear un fichero pdf en la ruta
     * indicada por el usuario
     *
     * @return
     */
    private File ficheroDestino() {
        //cuadro de diálogo 'guardar como' de Java...
        JFileChooser fc = new JFileChooser();
        //...posicionado en el archivo de nombre tomado de la url
        fc.setSelectedFile(new File(cadenaURL.substring(cadenaURL.lastIndexOf("/"))
                + (cadenaURL.endsWith(".pdf") ? "" : ".pdf")));
        //muestra el cuadro de diálogo en pantalla
        int showSaveDialog = fc.showSaveDialog(null);
        //si se pulsa 'Aceptar'
        if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
            //devuelve el archivo indicado por el usuario
            return fc.getSelectedFile();
        }
        //devuelve nulo
        return null;
    }

}

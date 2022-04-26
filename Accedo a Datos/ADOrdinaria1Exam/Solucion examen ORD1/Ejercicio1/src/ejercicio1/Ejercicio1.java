/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/* Voy añadiendo los import que necesito */
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
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
        /* Lo primero creo un objeto para cargar todos los datos que tengo que pasar al XML */
        Grupo band = new Grupo();
        ArrayList<Grupo> datos = band.cargarDatos();
        
        /* Ahora que ya tengo todos los datos cargados en el ArrayList creo el XML y los voy cargando en él */
        try {
            /* Creo el DOM */
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            /* Creo el elemento raiz */
            Document doc = db.newDocument();
            Element raiz = doc.createElement("discografica");
            doc.appendChild(raiz);
            
            /* Ahora voy creando cada uno de los elementos grupo con su información y añadiendo cada elemento 
               donde corresponda */
            for(Grupo gru : datos){
                /* Creo cada elemento grupo y se los voy añadiendo a la raiz del documento */
                Element grupo = doc.createElement("grupo");
                grupo.setAttribute("nombre", gru.getNombre());
                grupo.setAttribute("estilo", gru.getEstilo());
                Element componentes = doc.createElement("componentes");
                grupo.appendChild(componentes);
                /* Como cada grupo tiene varios componentes tengo que hacer otro bucle para rellenar los campos */
                for(Componente com : gru.getComponentes()){
                    Element componente = doc.createElement("componente");
                    componente.setAttribute("instrumento", com.getInstrumento());
                    componente.setTextContent(com.getNombre());
                    componentes.appendChild(componente);
                }
                /* Como cada grupo tiene varios discos tengo que hacer otro bucle para rellenar los campos */
                Element discos = doc.createElement("discos");
                grupo.appendChild(discos);
                /* Como cada grupo tiene varios discos tengo que hacer otro bucle para rellenar los campos */
                for(Disco dis : gru.getDiscos()){
                    Element disco = doc.createElement("disco");
                    disco.setAttribute("orden", Integer.toString(dis.getOrden()));
                    disco.setTextContent(dis.getNombre());
                    discos.appendChild(disco);
                }
                /* Cuando cargo todos los datos del elemento grupo se le añado a la raiz */
                raiz.appendChild(grupo);
            }
            
            /* Cuando ya tengo creado todo el DOM lo voy a escribir en el documento XML */
            /* Lo primero es crear el transformer */
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer tra = tf.newTransformer();
            /* Despues cargo la fuente del DOM */
            DOMSource fuente = new DOMSource(doc);
            /* Creo la salida hacia el documento que quiero crear */
            StreamResult salida = new StreamResult(new File("discografica.xml"));
            /* Establezco que meta un intro despues de cada linea, para que el XML de salida se vea bien */
            tra.setOutputProperty(OutputKeys.INDENT, "yes");
            /* Y hago la transformación con los dos últimos elementos creados */
            tra.transform(fuente, salida);

            /* Aviso por consola que el archivo se ha creado correctamente */
            System.out.println("\nArchivo discografica.xml creado correctamente");
        
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
    
}

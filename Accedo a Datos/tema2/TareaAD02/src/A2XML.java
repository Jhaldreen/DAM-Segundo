
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Antonio
 */
public class A2XML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File fichero = new File("fichero\\Empleados.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
        int codigo, pos = 0;// nos ponemos al principio del fichero
        char nombre[] = new char[06], aux;
        char direccion[] = new char[15], aux1;
        float salario, comision;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = (Document) implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0"); // Versión de XML con la que vamos a trabajar

            for (;;) {
                file.seek(pos);
                codigo = file.readInt();// se obtiene identificador empleado
                for (int i = 0; i < nombre.length; i++) {
                    aux = file.readChar();// recorremos los caracteres del nombre
                    nombre[i] = aux;//se guardan en un vector los caracteres del nombre
                    // convertimos el vector a cadena
                }// hacemos lo mismo con el apellido
                for (int i = 0; i < direccion.length; i++) {
                    aux1 = file.readChar();
                    direccion[i] = aux1;

                }
                String nombreS = new String(nombre);
                String direccionS = new String(direccion);
                salario = file.readFloat();
                comision = file.readFloat();

                if (codigo > 0) {
                    Element raiz = document.createElement("empleado");
                    document.getDocumentElement().appendChild(raiz);
                    CrearElemento("codigo", Integer.toString(codigo), raiz, document);
                    CrearElemento("nombre", nombreS.trim(), raiz, document);
                    CrearElemento("direccion", direccionS.trim(), raiz, document);
                    CrearElemento("salario", Float.toString(salario), raiz, document);
                    CrearElemento("codigo", Float.toString(comision), raiz, document);
                }
                pos = pos + 54;// nos posicionamos en la siguiente posicion
                if (file.getFilePointer() == file.length()) {
                    break;
                }
            }// se va recorriendo el fichero
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("fichero\\Empleados.xml"));  // Nombre del fichero XML      
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            Result console = new StreamResult(System.out);
            transformer.transform(source, console);

        } catch (ParserConfigurationException | DOMException | IOException | TransformerException e) {
            System.err.println("Error: " + e);
            file.close();
        }
    }

    static void CrearElemento(String datoEmple, String valor,
            Element raiz, Document document) {
        Element elem = document.createElement(datoEmple); // Creamos el nodo hijo
        Text text = document.createTextNode(valor); // Se le da el valor
        raiz.appendChild(elem); // Pegamos el elemento hijo a la raiz
        elem.appendChild(text); // Pegamos el valor del nodo	 	
    }

}

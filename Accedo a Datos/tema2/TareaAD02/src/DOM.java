
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Antonio
 */
public class DOM {

    private void visualizarEtiquetasXMLconDOM() {

        // Creo una instancia de DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Obtengo el documento, a partir del XML
            Document documento = builder.parse(new File("fichero\\Libros.xml"));
            documento.getDocumentElement().normalize();
            NodeList libros = documento.getElementsByTagName("libro");
            // Recorro las etiquetas
            for (int i = 0; i < libros.getLength(); i++) {
                System.out.println("Elemento: " + documento.getDocumentElement().getNodeName());
                // Cojo el nodo
                Node nodo = libros.item(i);
                //Obtengo la información de ese nodo
                getInformacionNodo(nodo);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void getInformacionNodo(Node nodo) {
        //Cogemos el atributo "año" del libro
        NamedNodeMap attributes = nodo.getAttributes();
        if (attributes.getLength() > 0) {
            Node item = attributes.item(0);
            System.out.println("Año de publicación " + item.getTextContent());
        }
        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
            Element e = (Element) nodo;
            // Obtengo sus hijos y los recorro
            NodeList hijos = e.getChildNodes();
            for (int j = 0; j < hijos.getLength(); j++) {
                Node hijo = hijos.item(j);
                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                    String text = hijo.getTextContent().trim();
                    //Como el autor está separado por nombre y apellido sustituimos los saltos
                    //de linea y espacios para dejarlo en una línea
                    if (text.contains("\n")) {
                        text = text.replace("\n", " ")
                                .replaceAll("\\s+", ",");
                    }
                    // Muestro el contenido
                    System.out.println(hijo.getNodeName() + ": " + text);
                }

            }
            System.out.println();
        }
    }

    private String getNodo(String etiqueta, Element element) {
        NodeList nodeList = element.getElementsByTagName(etiqueta);
        Node valor = nodeList.item(0);
        return valor.getNodeValue();
    }

    public static void main(String[] args) throws SAXException, IOException {
        System.out.println("**********************************\n"
                + "Documento Libros.xml leido con DOM\n"
                + "**********************************\n"
                + "----------------------------------");
        DOM dom = new DOM();
        dom.visualizarEtiquetasXMLconDOM();

    }
}

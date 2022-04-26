
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Antonio
 */
public class SAX extends DefaultHandler {

    private ArrayList<Libro> libro = new ArrayList();//alamceno las versiones que leo del documento
    private Libro libros;//para poder ir leyendo del xml y luego poder guardarlo en el array
    private final StringBuilder sb = new StringBuilder();// para poer leer los elemento simples

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(ch, start, length);
    }

    public ArrayList<Libro> getLibro() {
        return libro;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            //pongo el cierre de etiquetas que me haga falta,sino elimino
            //no pasa nada por dejarlas
            case "titulo":
                libros.setTitulo(sb.toString());
                break;
            case "nombre":
                libros.setNombre(sb.toString());
                break;
            case "apellido":
                libros.setApellido(sb.toString());
                break;
            case "editorial":
                libros.setEditorial(sb.toString());
                break;
            case "precio":
                libros.setPrecio(Float.parseFloat(sb.toString()));
                break;

        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        switch (qName) {

            case "libro":
                libros = new Libro();
                libro.add(libros);
                libros.setYear(Integer.parseInt(attributes.getValue("año")));
                break;
            case "titulo":
                sb.delete(0, sb.length());//ahora hay que vaciar el buffer
                break;
            case "autor":// no hace falta cerrrar con break con editorial vale
            case "nombre":
            case "apellido":
            case "editorial":
                sb.delete(0, sb.length());//ahora hay que vaciar el buffer
                break;
            case "precio":
                sb.delete(0, sb.length());//ahora hay que vaciar el buffer
                break;

        }
    }

    private class Libro {

        private int year;
        private String titulo;
        private String apellido;
        private String nombre;
        private String editorial;
        private Float precio;

        public Libro() {
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEditorial() {
            return editorial;
        }

        public void setEditorial(String editorial) {
            this.editorial = editorial;
        }

        public Float getPrecio() {
            return precio;
        }

        public void setPrecio(Float precio) {
            this.precio = precio;
        }

        @Override
        public String toString() {// para poder mostrar el objeto por la consola
            return "Elemento libro\nAño de publicación: " + year + "\nTitulo: " + titulo + "\nAutor: " + nombre + apellido + "\nEditorial: " + editorial + "\nPrecio: " + precio + "\n";
        }
    }

    public static void main(String[] args) throws SAXException, IOException {
        SAX sax = new SAX();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(sax);
        InputSource inputSource = new InputSource("fichero\\Libros.xml");
        xmlReader.parse(inputSource);
        ArrayList<Libro> libro = sax.getLibro();
        System.out.println("**********************************\n"
                + "Documento Libros.xml leido con SAX\n"
                + "**********************************\n"
                + "----------------------------------");
        for (Libro libro1 : libro) {
            System.out.println(libro1);

        }
    }

}

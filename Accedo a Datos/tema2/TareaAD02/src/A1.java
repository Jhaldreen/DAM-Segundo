
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Antonio
 */
public class A1 {

    private int codigo;
    private String nombre;
    private String direccion;
    private float salario;
    private float comision;

    public A1(int codigo, String nombre, String direccion, float salario, float comision) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.salario = salario;
        this.comision = comision;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File f = new File("fichero\\Empleados.dat");
        ArrayList<A1> empleados = new ArrayList<>();

        empleados.add(new A1(1, "PEPE", "Federico Vial", 10, 1500));
        empleados.add(new A1(2, "Juan", "General Davila", 20, 1600));
        empleados.add(new A1(3, "Ramon", "Calle Falsa 123", 30, 1700));
        empleados.add(new A1(4, "Maria", "La Albericia", 40, 1600));
        empleados.add(new A1(5, "Lucia", "Peña castillo", 50, 1900));

        try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {// no necesito cerrar al final con esta manera
            int pos = 0;

            for (A1 empleado : empleados) {

                raf.writeInt(empleado.codigo);

                StringBuffer sb = new StringBuffer(empleado.getNombre());
                sb.setLength(06);//no tengo mas de 6 caracteres si es mas se corta
                raf.writeChars(sb.toString());//nombre

                StringBuffer bs = new StringBuffer(empleado.getDireccion());
                bs.setLength(15);//para la direccion necesito 15 caracte por lo menos
                raf.writeChars(bs.toString());//direccion

                raf.writeFloat(empleado.comision);
                raf.writeFloat(empleado.salario);
            }

           //raf.seek(54);
            // suma de bytes para posicionarnos en el siguiente alumno siendo 0 el primero
            //54 la segunda posicion 4 bytes de int 12 del String del nombre 2 bytes por cada char
            //30 de la direccion y 4 por cada float

            System.out.println(raf.readInt());
            String nombre = "";
            for (int i = 0; i < 6; i++) {
                nombre += raf.readChar();

            }
            String direccion = "";
            for (int i = 0; i < 15; i++) {
                direccion += raf.readChar();
            }
            System.out.println(nombre);
            System.out.println(direccion);
            System.out.println(raf.readFloat());
            System.out.println(raf.readFloat());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }

}

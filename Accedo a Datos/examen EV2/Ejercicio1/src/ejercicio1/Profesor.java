/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/* Voy añadiendo los import necesarios */
import java.util.ArrayList;

/**
 *
 * @author DIEGO
 */
public class Profesor {
    /* Creo los atributos de la clase */
    String nombre;
    String apellidos;
    String dni;
    String telefono;
    int numAsignaturas;
    ArrayList<Asignatura> asignaturas;
    
    /* Creo el constructor de la clase */
    public Profesor(String nombre, String apellidos, String dni, String telefono, int numAsignaturas, ArrayList<Asignatura> asignaturas) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.numAsignaturas = numAsignaturas;
        this.asignaturas = asignaturas;
    }

    /* Creo los getter y setter de la clase */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public int getNumAsignaturas() {
        return numAsignaturas;
    }

    public void setNumAsignaturas(int numAsignaturas) {
        this.numAsignaturas = numAsignaturas;
    }
    
    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

}

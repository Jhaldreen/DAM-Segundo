/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaquetePrincipal;

/**
 *
 * @author Antonio
 */
public class Hijo {
//creamos las variables que se necesiten y le creamos un constructor. Creamos sus getter y setter
    private String nombre;
    private int edad;

    public Hijo(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    //Aqui también igual que en los jefes que siemrpe se me olvida el toString
    //y luego no salen las cosas
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", edad: " + edad;
    }

}

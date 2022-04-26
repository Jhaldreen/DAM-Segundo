/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/**
 *
 * @author DIEGO
 */
public class Disco {
    /* Añado los atributos de la clase */
    String nombre;
    int orden;
    
    /* Añado el constructor */
    public Disco(String nom, int ord) {
        this.nombre = nom;
        this.orden = ord;
    }
    
    /* Añado los getter y setter */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
    
}

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
public class Componente {
    /* Creo los atributos de la clase */
    String instrumento;
    String nombre;
    
    /* Añado el constructor */
    public Componente(String ins, String nom){
        this.instrumento = ins;
        this.nombre = nom;
    }

    /* Añado los getter y setter */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }
    
}

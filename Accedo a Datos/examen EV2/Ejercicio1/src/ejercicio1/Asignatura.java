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
public class Asignatura {
    /* Creo los atributos de la clase */
    int id;
    String nombre;
    int curso;
    int horas;

    /* Creo el constructor de la clase */
    public Asignatura(int id, String nombre, int curso, int horas) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.horas = horas;
    }

    /* Creo todos los getter y setter de la clase */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
}

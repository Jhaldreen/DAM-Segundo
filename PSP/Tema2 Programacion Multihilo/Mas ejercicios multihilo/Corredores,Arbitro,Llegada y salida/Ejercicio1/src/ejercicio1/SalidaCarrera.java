/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Objeto compartido por todos los hilos que actuará como monitor
 * @author alumnoddam
 */
public class SalidaCarrera {
    
    /**
     * Indica si la carrera ha comenzado o nó
     */
    private boolean comienzaCarrera;
    
    /**
     * Constructor por defecto
     */
    public SalidaCarrera(){
        comienzaCarrera = false;
    }
    
    /**
     * Método que llamaran los corredores, en el que los pone a espera hasta que
     * haya comenzado la carrera
     * @param numeroAlumno Número del corredor o alumno
     */
    public synchronized void llegaCorredor(int numeroAlumno){
        
        if (!comienzaCarrera){
            System.out.println("Corredor " + numeroAlumno + " llegó pero la " + 
                    "carrera no ha comenzado todavía. Esperando en la salida.");
            //Si no ha comenzado la carrera, suspendemos el thread
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Error inesperando cuando el corredor " + 
                        numeroAlumno + " se intentó suspender");
                ex.printStackTrace();
            }
        } else {
            //Dada la lógica actual del programa, no es posible que un corredor
            //llegue antes de que el árbitro comienze la carrera, pero por si
            //quisieramos cambiarlo mas adelante...
            System.out.println("Corredor " + numeroAlumno + " llegó.");
        }
    }
    
    /**
     * Inicia la carrera. Todos los corredores que estén esperando indican su
     * número y dicen que empiezan a correr
     * @param nombreProfesor 
     */
    public synchronized void disparoArbitro(String nombreProfesor){
        System.out.println("Arbitro " + nombreProfesor + ". PUM!!!");
        //Despertamos a todos los corredores que estaban en la espera
        notifyAll();
		comienzaCarrera = true;
    }
}

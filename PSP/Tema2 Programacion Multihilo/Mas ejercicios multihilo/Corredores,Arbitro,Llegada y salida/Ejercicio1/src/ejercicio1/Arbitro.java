/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Espera x segundos e inicia la carrera
 * Realmente no es necesario que ocupe un Thread por cuenta propia, pero por si
 * quisieramos extender el programa mas adelante lo volveremos un Thread también
 * @author alumnoddam 
 */
public class Arbitro extends Thread{
    /**
     * Número de segundos que esperará antes de iniciar la carrera
     */
    private float retrasoSegundos = 5;
    
    /**
     * Nombre del árbitro
     */
    private String nombre;
    
    /**
     * Carrera que iniciará
     */
    private SalidaCarrera carrera;
    
    /**
     * Constructor que crea un Objeto Árbitro que espera retrasoSegundos antes de
     * iniciar la carrera
     * @param nombre Nombre del profesor/arbitro
     * @param retrasoSegundos Segundos que espera antes de iniciar la carrera
     * @param carrera Carrera que va a iniciar el árbitro
     */
    public Arbitro(String nombre, float retrasoSegundos, SalidaCarrera carrera){
        super();
        
        this.nombre = nombre;
        
        //Si los segundos que pasamos son mayor a 0, le asignamos el valor del
        //constructor. En caso contrario, tomará el valor de 5
        if(retrasoSegundos > 0)
          //  this.retrasoSegundos = retrasoSegundos;
        
        this.carrera = carrera;
    }
    
    /**
     * Inicia la espera de los segundos y después dispara la carrera. 
     */
    @Override
    public void run(){
        //Esperamos el tiempo indicado
        try {
            Thread.sleep((long) retrasoSegundos * 1000);
        } catch (InterruptedException ex) {
            System.err.println("Error al esperar para iniciar la carrera!");
            ex.printStackTrace();
        }
        
        //Comenzamos la carrera
        carrera.disparoArbitro(nombre);
    }
}

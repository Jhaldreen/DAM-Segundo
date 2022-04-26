/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Representa los corredores que participarán en la carrera
 * @author alumnoddam
 */
public class Corredor extends Thread {
    /**
     * Tiempo que tarda el corredor en llegar a la carrera
     */
    private float tiempoTardar = 1;
    
    /**
     * Número que identifica al corredor
     */
    private int numero;
    
    /**
     * Referencia a la carrera en la que participa
     */
    private SalidaCarrera carrera;
    
    
    /**
     * Construye un objeto corredor que participará en la carrera
     * @param numero Numero del corredor para poder identificarlo
     * @param tiempoTardar Tiempo que tardará en llegar a la carrera
     * @param carrera Carrera en la que va a participar
     */
    public Corredor(int numero, float tiempoTardar, SalidaCarrera carrera){
        super();
        
        this.numero = numero;
        
        //Si el tiempo que pasamos al constructor es menor a 0, le asingaremos
        //1 por defecto
        if(tiempoTardar > 0)
            this.tiempoTardar = tiempoTardar;
        
        this.carrera = carrera;
    }
    
    /**
     * Hace que el corredor llegue a la zona de inicio de la carrera, espera a
     * que esta comienze y después se dispone a correr
     */
    @Override
    public void run(){
        //simulamos el tiempo que tardaría en llegar a la carrera
        try {
            Thread.sleep((long) tiempoTardar * 1000);
        } catch (InterruptedException ex) {
            System.err.println("Error del corredor al tardar en llegar a la carrera");
            ex.printStackTrace();
        }
        
        //Le indicamos a la carrera que llegamos.
        carrera.llegaCorredor(numero);
        
        //La carrera ha comenzado, Run Forrest run!
        correComoLocoTio();
    }
    
    /**
     * Hacemos que el corredor corra como loco ... Venga profe, el humor no es nada
     * malo
     */
    private void correComoLocoTio(){
        //Esperamos entre 1 y 3 segundos para simular que está corriendo después
        //del disparo
        double esperar = 1 + (Math.random() * 2);
        try {
            sleep((long) (esperar * 1000));
        } catch (InterruptedException ex) {
            System.err.println("Error al interar correr como loco");
            ex.printStackTrace();
        }
        
        System.out.println("Corredor " + numero + " empieza a correr!!");
        
    }
    
}

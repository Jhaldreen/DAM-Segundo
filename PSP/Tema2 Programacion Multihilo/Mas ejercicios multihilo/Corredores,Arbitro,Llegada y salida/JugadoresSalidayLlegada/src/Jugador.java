/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio
 */
public class Jugador extends Thread{
    
    String nombre;
    OrdenSalida correr;
    boolean esJugador;

    public Jugador(String nombre, OrdenSalida correr, boolean esJugador) {
        this.nombre = nombre;
        this.correr = correr;
        this.esJugador = esJugador;
    }

   
    
 
    
    public void run (){
      try {
            Thread.sleep(1000);
            //Verifico si es personal que esta es jefe o no
            System.out.println(nombre + " esperando a la salida.");
             correr.llamadaArbitro(nombre);
              
           
        } catch (InterruptedException ex) {
            System.out.println("error");
        }

    }
    
}

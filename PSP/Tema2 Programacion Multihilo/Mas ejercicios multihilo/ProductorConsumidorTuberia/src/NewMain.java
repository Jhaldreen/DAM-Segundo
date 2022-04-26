/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Tuberia t = new Tuberia();          // creación de la tubería
        Productor p = new Productor(t);     // creación del productor, recibe la tubería como parámetro
        Consumidor c = new Consumidor(t);   // creación del consumidor, recibe la tubería como parámetro

        p.start();      // se lanza el hilo productor
        c.start();      // se lanza el hilo consumidor
    }
    
}

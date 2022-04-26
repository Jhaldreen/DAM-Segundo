/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio
 */
public class Arbitro extends Thread {
    
    String nombre;
    OrdenSalida ordenSalida;
    boolean arbitro;

    public Arbitro(String nombre, OrdenSalida ordenSalida, boolean arbitro) {
        this.nombre = nombre;
        this.ordenSalida = ordenSalida;
        this.arbitro = arbitro;
    }

  
    public void run (){
        try {
            Thread.sleep(1000);
            
            System.out.println("\n*******"+nombre+ " da el comienzo de la carrera*******");
            ordenSalida.salidaJugador(nombre);
        } catch (Exception e) {
        }
    
    
    }
    
}

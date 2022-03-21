/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuzonCorreoEjercicio;

import static java.lang.Thread.sleep;

/**
 *
 * @author jhald
 */
public class LectorHilo extends Thread {

    private BuzonCorreo bc;
    private String nombre;
    public LectorHilo(BuzonCorreo p_bc, String p_nombre) {
        
        bc = p_bc;
        nombre = p_nombre;
    }

    public void run() {
        
        for (int i = 0; i < 20; i++) {     
            
            System.out.println(nombre+" lee mensaje : "+bc.lee());
        }
        try {
            sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
            System.out.println("Interrupción del hilo...");
        }
    }
}

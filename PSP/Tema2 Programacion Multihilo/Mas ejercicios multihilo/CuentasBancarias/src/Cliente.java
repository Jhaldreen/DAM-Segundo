
import java.util.concurrent.RecursiveAction;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio
 */
public class Cliente extends Thread {
    
    
    private Cajero cajero;

    public Cliente( String nombre,Cajero cajero) {
        
        this.setName(nombre);
        this.cajero = cajero;
    }
    
    @Override
   public void run(){
    cajero.incrementaDinero();
        try {
           
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Dependiente.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}

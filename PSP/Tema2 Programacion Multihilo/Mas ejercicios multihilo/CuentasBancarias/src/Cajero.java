
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
public class Cajero {
    
    private int cuenta;//salidas y entradas de dinero
    
    public Cajero() {
        this.cuenta = 1000;
    }
    
    public synchronized void incrementaDinero(){
    
        try {
            
            wait();
            System.out.println("Dinero introducido");
        } catch (InterruptedException ex) {
            Logger.getLogger(Cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    cuenta++;
        System.out.println("Dinero en la cuenta------"+ cuenta);
    }
    
    public synchronized void decrementaDinero(){
    
        System.out.println("Sacaste dinero----");
        
        cuenta--;
        System.out.println("Dinero en la cuenta----"+ cuenta);
        
        notifyAll();
    
    }
}

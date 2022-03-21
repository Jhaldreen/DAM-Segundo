
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alumnoddam
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Buffer b = new Buffer();
        Productor p = new Productor(b);     // creación del productor, recibe el buffer como parámetro
        Consumidor c = new Consumidor(b);   // creación del consumidor, recibe buffer como parámetro

        for (int i = 0; i < 15; i++) {
           
             new Productor(b).start();    // se lanza el hilo productor
        }
        for (int i = 0; i < 15; i++) {
           
             new Consumidor(b).start();    // se lanza el hilo productor
        }
 

        try {
            p.join();      // se para el hilo productor
            c.join();      // se para el hilo consumidor
            
           System.out.println("FIN DEL PROGRAMA: BUFFER VACIO");
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 
    }

}

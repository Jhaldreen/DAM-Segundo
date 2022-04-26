package BarberosAmiManera;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Barberia barbero = new Barberia(false);
        for (int i = 0; i < 4; i++) {
             try {
                 Cliente c = new Cliente(i + 1, barbero);
                 Barbero b = new Barbero(i + 1, barbero);
                 c.start();
                 b.start();
                 c.join();
                 b.join();
             } catch (InterruptedException ex) {
                 Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
     
    }
}


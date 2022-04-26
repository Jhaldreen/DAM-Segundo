
import jdk.nashorn.api.tree.TryTree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        OrdenSalida or = new OrdenSalida();
        
        Jugador jug1 = new Jugador("Adri", or, false);
        Jugador jug2 = new Jugador("Elena", or, false);
        Jugador jug3 = new Jugador("Antonio", or, false);
        Arbitro ar1 = new Arbitro("Sara", or, true);
        
        jug1.start();
        jug2.start();
        jug3.start();
        ar1.start();
        
        try {
            jug1.join();
            jug2.join();
            jug3.join();
            ar1.join();
        } catch (Exception e) {
        }
    }
    
    
    
}

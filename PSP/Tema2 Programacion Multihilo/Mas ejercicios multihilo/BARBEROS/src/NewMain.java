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
         Barbero barbero = new Barbero(false);
        for (int i = 0; i < 4; i++) {
            Cliente c = new Cliente(i + 1, barbero);
            c.start();
        }
    }
    
}

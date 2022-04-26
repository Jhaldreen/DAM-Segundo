/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productor.consumidorweb;

/**
 *
 * @author Alfredo
 */
public class ProductorConsumidorWeb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Tuberia t = new Tuberia();
        Productor p = new Productor( t );
        Consumidor c = new Consumidor( t );

        p.start();
        c.start();
    }
    
}

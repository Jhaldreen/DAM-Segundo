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
       
        
        Controlador c = new Controlador(5);
        
        Productor p = new Productor(c, 1, 2);
//        Consumidor c = new Consumidor(c, 1, 2);
    }
    
}

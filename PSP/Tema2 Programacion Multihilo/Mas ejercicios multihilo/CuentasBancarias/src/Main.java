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
        Cajero c = new Cajero();
        
         for (int i = 1; i <= 10; i++)
        {
            (new Cliente("Entra " + i, c)).start();
        }//entrada de 10 hilos al jardín
        
        for (int i = 1; i <= 15; i++)
        {
            (new Dependiente("Sale " + i, c)).start();
        }//salida de 15 hilos al jardín
    }
    
}

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
        
        Globos g = new Globos();    //Instanciamos Globos
  //Bucle que crea 5 hilos de HinchaGlobo, le pasamos los globos e i para saber el número del hilo
        for (int i=1;i<=5;i++) {
        HinchaGlobo hg = new HinchaGlobo(g, i);
        hg.start();
        
        }
  //Bucle que crea 5 hilos de PinchaGlobo, le pasamos los globos e i para saber el número del hilo
        for (int i=1;i<=5;i++){
        PinchaGlobo ph = new PinchaGlobo(g, i);
        ph.start();
        
        
        }    
        
        
    }
    
}

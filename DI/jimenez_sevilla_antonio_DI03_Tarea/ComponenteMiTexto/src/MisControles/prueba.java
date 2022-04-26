/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisControles;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhald
 */
public class prueba {
    public static void main(String[] args){
      relojDigital rn = new relojDigital(23,59,57);
      
      
        while(true){
              System.out.println(rn.toString());
            rn.play();
          try {
              Thread.sleep(1000);
          } catch (InterruptedException ex) {
              Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      
    }
    
}

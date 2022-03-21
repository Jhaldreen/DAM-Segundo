/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhald
 */
public class Calculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //ejecuta la calculadora de Windows
            Process p = Runtime.getRuntime().exec("calc.exe");
            
            //Esperamos al proceso hasta que termina
            int exitCode = p.waitFor();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
           ex.printStackTrace();//errores estandar
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumnoddam
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Creamos la carrera
        SalidaCarrera carrera = new SalidaCarrera();
        Corredor[] corredores = new Corredor[15];
        
        //Creamos los corredores, y les decimos que se dirijan a la zona de inicio
        for(int i = 0; i < corredores.length; i++){
            corredores[i] = new Corredor(i+1, 1, carrera);
            corredores[i].start();
        }
        
        //Creamos el árbitro, y le decimos que inicie la carrera en 5 segundos
        Arbitro arbitro = new Arbitro("Joaquín Franco", 5, carrera);
        arbitro.start();
        
        //Esperamos a todos los corredores para indicar que ha terminado la carrera
        for(int i = 0; i < corredores.length; i++){
            try {
                corredores[i].join();
            } catch (InterruptedException ex) {
                System.err.println("Error al esperar al corredor");
                ex.printStackTrace();
            }
        }
        
        
        System.out.println("FIN DE LA CARRERA");
    }
    
}

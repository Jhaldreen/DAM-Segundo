
import static java.lang.Thread.sleep;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumnoddam
 */
public class Consumidor extends Thread {
      private Buffer buffer;

    public Consumidor(Buffer b) {
        buffer = b;
    }

   

    public void run() {

        int c;

// Consume 6 numeros del buffer

        for (int i = 0; i < 6; i++) 
        {

            c = buffer.lectura();  // recupera un numero a través del método sincronizado 

            // Imprime el numero recuperado
            System.out.println("Recojo el numero " + c +" de la posicion "+i);

            // Espera un poco antes de seguir cogiendo más letras (al azar, como máximo 2000ms )
            try {
                        sleep((int) (Math.random() * 2000));

            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }

    }
}

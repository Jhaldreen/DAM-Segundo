/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productor.consumidorweb;

import static java.lang.Thread.sleep;

/**
 *
 * @author Alfredo
 */
public class Consumidor extends Thread{
     private Tuberia tuberia;

    public Consumidor( Tuberia t ) {
        // Mantiene una copia propia del objeto compartido
        tuberia = t;
        }

     @Override
    public void run() {
        char c;

        // Consume 10 letras de la tubería
        for( int i=0; i < 10; i++ )
            {
            c = tuberia.recoger();
            // Imprime las letras retiradas
            System.out.println( "Recogido el caracter "+c );
            // Espera un poco antes de coger más letras
            try {
                sleep( (int)(Math.random() * 2000 ) );
            } catch( InterruptedException e ) {
                ;
            }
            }
        }
}

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
public class Productor extends Thread{
    private Tuberia tuberia;
    private String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Productor( Tuberia t ) {
        // Mantiene una copia propia del objeto compartido
        tuberia = t;
        }

    public void run() {
        char c;

        // Mete 10 letras en la tubería
        for( int i=0; i < 10; i++ )
            {
            c = alfabeto.charAt( (int)(Math.random()*26 ) );
            tuberia.lanzar( c );
            // Imprime un registro con lo añadido
            System.out.println( "Lanzado "+c+" a la tuberia." );
            // Espera un poco antes de añadir más letras
            try {
                sleep( (int)(Math.random() * 100 ) );
            } catch( InterruptedException e ) {
                ;
            }
            }
        }
}

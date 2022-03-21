/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumnoddam
 */
public class Productor extends Thread {
    
    private Buffer buffer;
    private int num;

    public Productor(Buffer b) {
        buffer = b;
    }
     public void run() {




        // Mete 6 nuemros en el buffer
        for (int i = 0; i < 6; i++) {
            //creamos  numeros aleatorios en el buffer
            int c = (int) (1 + (Math.random() * 6));

            buffer.escribir(c);

// Imprime un numero con lo añadido

            System.out.println("Puesto el numero " + c + " en buffer en la posicion "+i);

// Espera un poco antes de añadir más letras

            try {
                    sleep((int) (Math.random() * 100));

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

    }
    
    
}

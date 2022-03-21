/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumnoddam
 */
public class Buffer {
    
    private int buffer [] = new int[6];
    private int siguiente = 0;
       // banderas para saber el estado del buffer
    private boolean estaLlena = false;
    private boolean estaVacia = true;
    //No se como hacer que lea la ultima posicion ocupada, voy mal de tiempo
    //tampoco como escribir la primera posicion ocupada
    
     // Método SINCRONIZADO para retirar los numeros del buffer
    public synchronized int lectura() {

        // IMPORTANTE: NO se puede consumir si el buffer está vacío
        while (estaVacia == true) {
            try {
                wait(); // Se sale cuando estaVacia cambia a false
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Decrementa la cuenta, ya que va a consumir un numero
        siguiente--;
        // Comprueba si se retiró el ultimo numero
        if (siguiente == 0) {
            estaVacia = true;
        }
        // El buffer no puede estar lleno, porque acabamos de consumir
        estaLlena = false;
        notify();  //se notifica al monitor de sincronización para ejecute el siguiente hilo en espera (que esté en WAIT), productor o consumidor
        // Devuelve numero consumidor
        return (buffer[siguiente]);
    }
       // Método SINCRONIZADO para meter los numeros al buffer
    public synchronized void escribir(int c) {

        // Espera hasta que haya sitio para otro numero
        while (estaLlena == true) {
            try {
                wait(); // Se sale cuando estaLlena cambia a false
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Añade una letra en el primer lugar disponible
        buffer[siguiente] = c;
        // Cambia al siguiente lugar disponible
        siguiente++;
        // Comprueba si el buffer está lleno
        if (siguiente == 6) {
            estaLlena = true;
        }
        estaVacia = false; // no puede estar vacía porque se acaba de meter una letra
        notify();  //se notifica al monitor de sincronización para ejecute el siguiente hilo en espera (que esté en WAIT), productor o consumidor

    }
}

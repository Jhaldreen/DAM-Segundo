/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio
 */
public class Tuberia {

    private char buffer[] = new char[6];
    private int siguiente = 0;
    // banderas para saber el estado del buffer
    private boolean estaLlena = false;
    private boolean estaVacia = true;

    // Método SINCRONIZADO para retirar letras del buffer
    public synchronized char recuperar() {

        // IMPORTANTE: NO se puede consumir si el buffer está vacío
        while (estaVacia == true) {
            try {
                wait(); // Se sale cuando estaVacia cambia a false
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Decrementa la cuenta, ya que va a consumir una letra
        siguiente--;
        // Comprueba si se retiró la última letra
        if (siguiente == 0) {
            estaVacia = true;
        }
        // El buffer no puede estar lleno, porque acabamos de consumir
        estaLlena = false;
        notify();  //se notifica al monitor de sincronización para ejecute el siguiente hilo en espera (que esté en WAIT), productor o consumidor
        // Devuelve la letra al thread consumidor
        return (buffer[siguiente]);
    }

    // Método SINCRONIZADO para meter letras por la tubería
    public synchronized void introducir(char c) {

        // Espera hasta que haya sitio para otra letra
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

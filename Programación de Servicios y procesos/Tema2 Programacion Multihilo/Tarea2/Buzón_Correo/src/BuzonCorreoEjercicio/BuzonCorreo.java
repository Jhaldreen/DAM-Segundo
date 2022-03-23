/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuzonCorreoEjercicio;

/**
 *
 * @author jhald
 */
public class BuzonCorreo {

    private String mensaje = ""; //almacenamos el contenido de los mensajes
    private boolean vacio = true;//el buzon de salida está vacio
    //para poder ser leido en el buzón o no
    
    /* con este metodo alamcenamos un mensaje en el buzón siempre que esté vacio*/
    public synchronized void almacena(String men) {
        //cuando el buzon este vacio, lo notifico
     while(!vacio){     
         try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupcion del hilo...");
            }
        }
        vacio = false;
        mensaje = men;
        notifyAll();

     }
    /*Método lee. Si no está lleno, se bloquea hasta que se llene.
     Cuando está lleno, sale del bloqueo, indica que coge el contenido del BuzonCorreo, lo deja
     vacio y notifica a los procesos que puedan estar bloqueados esperando para llenarlo.*/
    public synchronized String lee() {
        while (vacio) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupcion del hilo...");
            }
        }
        vacio = true;
        notifyAll();
        return mensaje;
    }
}

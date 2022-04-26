
package BuzonCorreoEjercicio;

import static java.lang.Thread.sleep;

/**
 *
 * @author jhald
 */
public class  EscritorHilo extends Thread {

private BuzonCorreo bc;//clase donde encapsulamos en buzon común
private String nombre;//identificador de los nombres
    //contructor donde le masamos las variables
    public EscritorHilo(String p_nombre, BuzonCorreo p_bc) {
        bc = p_bc;
        nombre = p_nombre;
    }
     
@Override
    public void run(){//el hilo escritor invoca a alamcena para llenar el buzoncorreo 
       //hacemos un bucle para repetir 10 veces o 20 las que queramos
        for (int i = 0; i < 20; i++) {
            //imprimimos el nombre y el numero de mensaje
            bc.almacena(nombre+" escribe mensaje numero: "+i);
                 try{
                sleep((int)(Math.random()*1000));
            }catch(InterruptedException e){
                System.out.println("Interrupción del hilo...");
            }   
            
        }
        //se indica que ha terminado de escribir
        System.out.println(nombre+" ha dejado de escribir");
    }




}

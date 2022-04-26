
package ejercicio1;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalidaCarrera {
    
    //los corredores se preparan en la linea paralelamente(ejecutan el llegarCorredor), cuando el arbitro con el método que emplea, da el notify,
    //los hilos lanzan el último mensaje, y cada uno ejecuta su sleep, que será el rand entre 1 y 3 independiente para cada uno.
    //cuando todos acaben, cumplirán cada join y se ejecutará el mensaje final
    
    
    public synchronized void llegarCorredor(int numeroAlumno) throws InterruptedException{ //por este método sincronizaremos todos los hilos
        
       System.out.println("Corredor "+numeroAlumno+" llegó pero la carrera no ha comenzado todavía. Esperando en la salida.");
       wait();        //se queda en espera de un notify();

       System.out.println("Corredor "+numeroAlumno+" empieza a correr!"); //cuando queda liberado del wait(), realiza el resto de funciones

       }
    
    public synchronized void disparoArbitro(String nombreProfesor){         //tambien debe estar sincronizado para poder influir en los demás hilos  
        
        System.out.println("Árbitro "+nombreProfesor+" PUM!!!");
        
        notifyAll();        //desbloqueamos todos los hilos
    }
}

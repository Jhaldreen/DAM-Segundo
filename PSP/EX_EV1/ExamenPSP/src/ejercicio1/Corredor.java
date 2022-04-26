package ejercicio1;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Corredor implements Runnable { //runnable para poder ser montado en un hilo

SalidaCarrera sc;
int numero;

    public SalidaCarrera getSc() {
        return sc;
    }

    public void setSc(SalidaCarrera sc) {
        this.sc = sc;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Corredor(int num,SalidaCarrera sscc){
        
        this.numero=num;
        this.sc=sscc;    //el objeto SalidaCarrera será común para todos los corredores y el árbitro       
    }

    public void run(){
        
    try {
        sleep(1000);        //1 segundo para llegar a la carrera
        this.sc.llegarCorredor(this.getNumero());       //el hilo ejecuta el llegarCorredor de su objeto, dando su número como argumento
        
        double rand=Math.random();  //número aleatorio entre 0 y 1

      sleep((long)(2000*rand+1000));  //espera entre 1 y 3 s
        
    } catch (InterruptedException ex) {
        Logger.getLogger(Corredor.class.getName()).log(Level.SEVERE, null, ex);
    }
    
           
    }
}

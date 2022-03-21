package ejercicio1;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arbitro implements Runnable {      //runnable para poder sen montado en un hilo
    public String Nombre;//inicializado
    public SalidaCarrera sc;

    public Arbitro(String Nombre, SalidaCarrera sc) {//
        this.Nombre = Nombre;
        this.sc = sc;
    }
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public void run(){
        
        try {
            sleep(5000); //espera inicial de 5s nada más ser creado
            
            this.sc.disparoArbitro(this.getNombre());//ejecución del método de su objeto SalidaCarrera
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Arbitro.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    
}

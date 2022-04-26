

/**
 *
 * @author Antonio
 */
public class OrdenSalida {
    
      private boolean llegar = true;
    
    
    public synchronized String salidaJugador(String nombre){
        
      
    while(!llegar){
        try {
            wait();
            
        } catch (Exception e) {
        }
    }
    llegar=true;
    return nombre;
    
    }
    public synchronized void llamadaArbitro (String nombre){
    if(llegar){
        try {
            System.out.println("\n**** "+nombre+" ha llegado a la meta ");
            notifyAll();
        } catch (Exception e) {
        }
    }
    }
    
}

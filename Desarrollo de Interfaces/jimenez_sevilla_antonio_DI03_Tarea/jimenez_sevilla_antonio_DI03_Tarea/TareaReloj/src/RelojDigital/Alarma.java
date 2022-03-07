//Esta es la clase de la alarma
package RelojDigital;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Antonio
 */
public class Alarma implements Serializable {
   
    private Date horaAlarma;
    private boolean activa;
    
    
    public Alarma (Date horaAlarma, boolean activa){ 
        this.horaAlarma = horaAlarma;
        this.activa = activa;
    }
    
    public Date getHoraAlarma(){
        return horaAlarma;
    }
    
    public void setHoraAlarma(Date horaAlarma){
        this.horaAlarma = horaAlarma;
    }
    
    public boolean isActiva(){
        return activa;
    }
    
    public void setActiva(boolean activa){
        this.activa = activa;
        
    }
}

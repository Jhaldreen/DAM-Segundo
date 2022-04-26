
package MisControles;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jhald
 */
public class Alarma implements Serializable {
    
     private boolean activa;
     private Date horaAlarma;

    public Alarma(boolean activa, Date horaAlarma) {
        this.activa = activa;
        this.horaAlarma = horaAlarma;
    }

  
    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Date getHoraAlarma() {
        return horaAlarma;
    }

    public void setHoraAlarma(Date horaAlarma) {
        this.horaAlarma = horaAlarma;
    }
     
    
}

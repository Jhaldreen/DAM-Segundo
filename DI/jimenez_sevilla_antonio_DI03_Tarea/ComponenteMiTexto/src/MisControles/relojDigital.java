package MisControles;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventListener;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Antonio
 */
public class relojDigital extends JLabel implements Serializable {

    private boolean formatoHora;
    private Alarma alarma;
    private int hora, minuto, segundos;
    String mensaje;
    private eventoFinAlarma receptor;

    private SimpleDateFormat sdf24h = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat sdf12h = new SimpleDateFormat("hh:mm:ss a");

    public relojDigital(int hora, int minuto, int segundos) {
        this.hora = hora;
        this.minuto = minuto;
        this.segundos = segundos;
    }

    public void play() {//SUMA un segundo

        if (segundos < 59) {
            segundos = segundos + 1;
        } else {
            segundos = 0;
            if (minuto < 59) {
                minuto = minuto + 1;
            } else {
                minuto = 0;
                if (hora < 23) {
                    hora = hora + 1;
                } else {
                    hora = 0;
                }
            }
        }
    }

    public String toString() {
        
        
        return this.hora+":"+this.minuto+":"+this.segundos;
        
        
       
    }

}

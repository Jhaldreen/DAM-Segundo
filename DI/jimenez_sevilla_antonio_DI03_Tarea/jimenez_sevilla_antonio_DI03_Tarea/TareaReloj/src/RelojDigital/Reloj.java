
package RelojDigital;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author Antonio
 */
public class Reloj extends JLabel implements Serializable {

    private boolean formato24; // propiedad que si está a true el formato será 24h, si no,false 12h
    private Alarma alarma; //

    private SimpleDateFormat format24 = new SimpleDateFormat("HH:mm:ss"); //H mayúscula para 24h
    private SimpleDateFormat format12 = new SimpleDateFormat("hh:mm:ss a"); //para formato 12h

    private AlarmaListener alarmaListener;

    //Constructor sin parametros
    public Reloj() {
        //clase para cuando se quiere ejecutar algo varias veces con un tiempo de separación
        //más abajo está el tiempo con el que se quiere ejecutar
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            // para mostrar la hora en el JLabel
            @Override
            public void run() {
                Date horaActual = new Date();
                if (formato24) {
                    setText(format24.format(horaActual));
                } else {
                    setText(format12.format(horaActual));
                    //aquí acaba el código para mostrar la hora en el JLabel
                }
                if (alarma != null) {

                    System.out.println(horaActual);
                    System.out.println(alarma.getHoraAlarma());
                    if (alarma.isActiva() && horasCoinciden(horaActual, alarma.getHoraAlarma())) {

                        if (alarmaListener != null) {
                            alarmaListener.suenaAlarma();
                        }

                    }

                }
            }

            private boolean horasCoinciden(Date horaActual, Date horaAlarma) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }, 0, 1000); //tiempo = 0 segundos (que se ejecute ya) y mil milisegundos que se ejecute 1 vez cada segundo
        //para que se actualice la hora una vez por segundo
    }

    public boolean isFormat024() {
        return formato24;
    }

    public void setFormato24(boolean formato24) {
        this.formato24 = formato24;
    }

    public void setAlarma(Alarma alarma) {
        this.alarma = alarma;
    }

    public void addAlarmaListener(AlarmaListener alarmaListener) {

        this.alarmaListener = alarmaListener;
    }

    private boolean horasCoinciden(Date horaActual, Date horaAlarma, Calendar Calendar) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaActual);
        int horasActual, minActual, segActual, horasAlarma, minAlarma, segAlarma;
        horasActual = calendar.get(Calendar.HOUR_OF_DAY);
        minActual = calendar.get(Calendar.MINUTE);
        segActual = calendar.get(Calendar.SECOND);
        calendar.setTime(horaAlarma);
        horasAlarma = calendar.get(Calendar.HOUR_OF_DAY);
        minAlarma = calendar.get(Calendar.MINUTE);
        segAlarma = calendar.get(Calendar.SECOND);

        return horasActual == horasAlarma && minActual == minAlarma && segActual == segAlarma;

    }

}

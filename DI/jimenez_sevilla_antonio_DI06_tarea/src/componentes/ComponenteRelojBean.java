/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Antonio
 */
public class ComponenteRelojBean extends JLabel  implements ActionListener, Serializable  {
    
    public boolean modo24;
    public boolean activar_Alarma;
    public int Minutos_Alarma;
    public int Hora_Alarma;
    
    public String Mensaje_Alarma;
    
 
    /**
     * Get the value of Mensaje_Alarma
     *
     * @return the value of Mensaje_Alarma
     */
    public String getMensaje_Alarma() {
        return Mensaje_Alarma;
    }

    /**
     * Set the value of Mensaje_Alarma
     *
     * @param Mensaje_Alarma new value of Mensaje_Alarma
     */
    
    public void setMensaje_Alarma(String Mensaje_Alarma) {
        this.Mensaje_Alarma = Mensaje_Alarma;
    }


    private final Timer t;
    private Calendar calendario;
    private DefinirAlarmaListener receptor;

    private SimpleDateFormat sdf24h = new SimpleDateFormat("HH:mm:ss"); //formatea la hora a formato 24h
    private SimpleDateFormat sdf12h = new SimpleDateFormat("hh:mm:ss a"); //formatea la hora a formato 12h
    
    /**
     * Get the value of activar_Alarma
     *
     * @return the value of activar_Alarma
     */
    
    public boolean isActivar_Alarma() {
        return activar_Alarma;
    }

    /**
     * Set the value of activar_Alarma
     *
     * @param activar_Alarma new value of activar_Alarma
     */
    
    public void setActivar_Alarma(boolean activar_Alarma) {
        this.activar_Alarma = activar_Alarma;
    }


    /**
     * Get the value of Minutos_Alarma
     *
     * @return the value of Minutos_Alarma
     */
    
    public int getMinutos_Alarma() {
        return Minutos_Alarma;
    }

    
    public void setMinutos_Alarma(int Minutos_Alarma) {
        this.Minutos_Alarma = Minutos_Alarma;
    }


    /**
     * Get the value of Hora_Alarma
     *
     * @return the value of Hora_Alarma
     */
    
    public int getHora_Alarma() {
        return Hora_Alarma;
    }

    /**
     * Set the value of Hora_Alarma
     *
     * @param Hora_Alarma new value of Hora_Alarma
     */
    
    public void setHora_Alarma(int Hora_Alarma) {
        this.Hora_Alarma = Hora_Alarma;
    }

    public boolean isModo24() {
        return modo24;
    }
    
    public void setModo24(boolean modo24) {
        this.modo24 = modo24;
    }

    /**
     * Set the value of modo
     *
     * @param modo new value of modo
     */
    
    public interface DefinirAlarmaListener extends EventListener
    {
        public void capturarAlarma(DefinirAlarmaEvent ev);
    }
    
    public ComponenteRelojBean(){
 
        t = new Timer (1000, this);
        t.start();
   
    
    /* Si queremos que cuando se cree el frame con la Jlabel pinte la hora y no
     * pase un segundo con el frame vacio tenemos que activar esta parte
     */
    
/*
        Date horaActual = new Date(); //Se saca la hora actual
        
        //En función de si el formato es 24h o 12h, se saca una hora u otra
        
        if (modo24) {
            setText(sdf24h.format(horaActual));
        }else{
            setText(sdf12h.format(horaActual));
        }
   
*/
       

    }
    


   public void actionPerformed(ActionEvent e)
    {

        calendario = Calendar.getInstance();
          
        Date horaActual = new Date(); //Se obtiene la hora actual
        
        //En función de si el formato es 24h o 12h, se saca una hora u otra
        
        if (modo24) {
            setText(sdf24h.format(horaActual));
        }else{
            setText(sdf12h.format(horaActual));
        }

        repaint();
        
        /**
         *
         * Gestion de las alarmas, si coincide se lanza el evento
         *
         */
        
        int hora, min;
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        min = calendario.get(Calendar.MINUTE);
        
        if(activar_Alarma)
        {
            if ((hora==Hora_Alarma) && (min==Minutos_Alarma)){
                receptor.capturarAlarma( new DefinirAlarmaEvent(this));
            }
        }
    }
   
     public void addDefinirAlarmaListener(DefinirAlarmaListener receptor){
        this.receptor = receptor;
    }

    public void removeDefinirAlarmaListener(DefinirAlarmaListener receptor){
        this.receptor=null;
    }
}

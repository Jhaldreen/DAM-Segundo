/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reloj;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author jhald
 */
public class Reloj extends Thread{

    @Override
    public void run() {
       while(true){
       play();//llamamos al metodo play 
           try {
               Thread.sleep(1000);//hilo para que avance un segundo
           } catch (InterruptedException ex) {
               Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
   
    private int hora, minuto, segundos;
    private JLabel label ;
    private boolean formato24h;

    public Reloj(int hora, int minuto, int segundos, JLabel label) {
        this.hora = hora;
        this.minuto = minuto;
        this.segundos = segundos;
        this.label = label;
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
        label.setText(toString());
    }

    public String toString24() {// para mostrar el reloj
        String resultado = "";
        
        return digitos(this.hora)+":"+digitos(this.minuto)+":"+digitos(this.segundos);     
    }
    public String toString12() {// para mostrar el reloj
      int h = hora%12;
      if (hora <12 )
      
        return digitos (h)+":"+this.minuto+":"+this.segundos +" AM";
      else
          return digitos (hora)+":"+this.minuto+":"+this.segundos +" PM";
    }
    public String digitos(int n){
         if (n <10){
        return  "0"+n;// dar formato a las horas
        }
        return String.valueOf(n);
    }  
}

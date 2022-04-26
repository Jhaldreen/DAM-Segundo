/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentemitexto;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import javax.swing.JTextField;


/**
 *
 * @author Angel
 */
public class ComponenteMiTexto extends JTextField implements Serializable {
    
    private Color color;
    private int ancho;
    private Font fuente;
    
    private int NumeroCaracteres;

    /**
     * Get the value of NumeroCaracteres
     *
     * @return the value of NumeroCaracteres
     */
    public int getNumeroCaracteres() {
        return NumeroCaracteres;
    }

    /**
     * Set the value of NumeroCaracteres
     *
     * @param NumeroCaracteres new value of NumeroCaracteres
     */
    public void setNumeroCaracteres(int NumeroCaracteres) {
        this.NumeroCaracteres = NumeroCaracteres;
    }

   
    public ComponenteMiTexto() {

    }
     /**
     * Get the value of color
     *
     * @return the value of color
     */
    public Color getColor() {
        return color;
    }
    /**
     * Set the value of color
     *
     * @param color new value of color
     */
    public void setColor(Color color) {
        this.color = color;
        this.setForeground(color);
    }   
    /**
     * Get the value of ancho
     *
     * @return the value of ancho
     */
    public int getAncho() {
        return ancho;
    }
    /**
     * Set the value of ancho
     *
     * @param ancho new value of ancho
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
        this.setColumns(ancho);
    }     
    /**
     * Get the value of fuente
     *
     * @return the value of fuente
     */
    public Font getFuente() {
        return fuente;
    }
    /**
     * Set the value of fuente
     *
     * @param fuente new value of fuente
     */
    public void setFuente(Font fuente) {
        this.fuente = fuente;
        this.setFont(fuente);
    }  
   
     
}

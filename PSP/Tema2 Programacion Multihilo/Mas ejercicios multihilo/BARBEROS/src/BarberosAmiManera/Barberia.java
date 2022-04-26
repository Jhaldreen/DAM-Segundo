/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarberosAmiManera;

/**
 *
 * @author Antonio
 */
public class Barberia {
     public boolean ocupado;
    public boolean[] sillas = new boolean[5];
  
    public Barberia(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public synchronized void ocuparSilla(int numClie) {
        System.out.println("Silla ocupada por el cliente número: " + numClie);
    }

    public synchronized void dejarSilla(int numClie) {
        while (isOcupado()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        System.out.println("El cliente " + numClie + " deja libre una silla");
    }

    public synchronized void inicioCorte(int numClie) {
        while (isOcupado()) {
            System.out.println("Barbero ocupado, " + numClie + " espera");
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        this.setOcupado(true);
        System.out.println("El barbero empieza a cortar el pelo al cliente " + numClie);
    }

    public synchronized void finCorte(int numClie) {
        this.setOcupado(false);
        System.out.println("El barbero termina de cortar el pelo al cliente " + numClie);
        notify();
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}

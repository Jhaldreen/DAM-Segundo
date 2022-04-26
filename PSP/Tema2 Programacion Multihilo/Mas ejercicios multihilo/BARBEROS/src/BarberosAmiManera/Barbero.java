/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarberosAmiManera;

import static java.lang.Thread.sleep;

/**
 *
 * @author Antonio
 */
public class Barbero extends Thread{
      private Barberia bar;
    private int numCliente;

    public Barbero(int nCli, Barberia b) {
        this.numCliente = nCli;
        this.bar = b;
    }

    @Override
    public void run() {
        try {
            bar.inicioCorte(numCliente);
            sleep(800);            
            bar.finCorte(numCliente);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

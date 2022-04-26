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
public class Cliente extends Thread {

    private Barberia bar;
    private int numCliente;

    public Cliente(int nCli, Barberia b) {
        this.numCliente = nCli;
        this.bar = b;
    }
    @Override
    public void run() {
        try {
            bar.ocuparSilla(numCliente);
            sleep(800);            
            bar.dejarSilla(numCliente);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

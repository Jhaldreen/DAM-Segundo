/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio
 */
public class Cliente extends Thread {

    private Barbero bar;
    private int numCliente;

    public Cliente(int nCli, Barbero b) {
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

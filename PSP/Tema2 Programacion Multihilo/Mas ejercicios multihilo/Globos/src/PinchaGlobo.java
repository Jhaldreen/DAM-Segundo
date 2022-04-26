/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio
 */
public class PinchaGlobo extends Thread{
    private Globos g;
    private int numero;
    public PinchaGlobo(Globos pg,int pnumero){
        g=pg;
        numero=pnumero;
        setName("PG"+numero);
        start();
    }
 
    @Override
    public void run(){
        int num;
        boolean nohaymas;
        do{
            try {Thread.sleep((int)(Math.random()*5000));} catch (Exception e) {}
            // Si no quedan globos tengo que dejar de pinchar
            nohaymas=g.pincho();
        } while (!nohaymas); // while true
    } // public run
} // public class
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea4_2;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public class ServidorMain {

    /**
     * @param args the command line arguments
     */
    static final int puerto = 2000;
    public static void main(String[] args) {
       
        
        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Escucho el puerto "+puerto);
          while(true){
          Socket socket = servidor.accept();
          Server hilo = new Server (socket);
          hilo.start();
          
          }
        } catch (IOException ex) {
            Logger.getLogger(ServidorMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}

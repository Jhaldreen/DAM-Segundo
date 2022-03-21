/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea4_3;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
            
            ServerSocket servidor = new ServerSocket(puerto); //creamos el servidor
            ArrayList<Usuarios> lista = new ArrayList();//lista para crear usuarios y contraseña
            lista.add(new Usuarios("usuario","usuario"));//añadimos usuario y contraseña
            lista.add(new Usuarios("admin","admin"));//añadimos otro usuario y contraseña
            
            
            
            try {
                
                System.out.println("Escucho el puerto "+puerto);
                for(;;){//Creamos un bucle infinito
                    Socket socket = servidor.accept();//Tiene un hilo dentro que esta constantemente esperando clientes
                    Server hilo = new Server (socket,lista); //creamos el hilo
                    hilo.start();//lanzamos el hilo
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(ServidorMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea4_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Antonio
 */
public class ClientMain {

    /**
     * @param args the command line arguments
     */
    public static final String HOST = "localhost";
    static final int Puerto = 2000;
  

    public static void main(String[] args) {
        int estado = 1;
  Scanner sc = new Scanner(System.in);
        try {
            //creamos un socket con la direccion IP del servidor y el puerto al que queremos conectarnos
            Socket skCliente = new Socket(HOST, Puerto);
            // Creo los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());
            // TAREAS QUE REALIZA EL CLIENTE
            
            System.out.println(flujo_entrada.readUTF());//recivo si estoy conectado
            do{
            String datos = flujo_entrada.readUTF();//recibo introduce archivo
            System.out.println(datos);
            String opcion = sc.nextLine();
            flujo_salida.writeUTF(opcion);
            switch (opcion){
                case "ls":
                    
                    int n = flujo_entrada.readInt();//recibe el numero de ficheros en el directorio
                    for (int i = 0; i < n; i++) {
                        System.out.println(flujo_entrada.readUTF());    
                    }
                    
            
                    break;  
                case "get":
                    System.out.println(flujo_entrada.readUTF());
                    String nombre = sc.nextLine();
                    flujo_salida.writeUTF(nombre);
                    int g = flujo_entrada.readInt();//numero de archivos totales
                    for (int i = 0; i < g; i++) {
                        System.out.println(flujo_entrada.readUTF());
                        
                    }
                    break;
            
            } 
            }while(estado != -1);
            skCliente.close();//cerramos el socket
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

}

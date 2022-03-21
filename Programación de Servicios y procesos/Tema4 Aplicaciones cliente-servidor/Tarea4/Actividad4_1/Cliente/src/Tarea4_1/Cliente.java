/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea4_1;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.net.Socket;

/**
 *
 * @author Antonio
 */
public class Cliente {
    
    static final int Puerto = 2000;
    static final String Host = "localhost";
     public Cliente() {

        try {
            // Me conecto al servidor en un determinado puerto
            Socket sCliente = new Socket(Host, Puerto);
            System.out.println("se conecta");

            // TAREAS QUE REALIZA EL  CLIENTE
            DataOutputStream flujo_salida = new DataOutputStream(sCliente.getOutputStream());
            DataInputStream flujo_entrada = new DataInputStream(sCliente.getInputStream());
          
            String nombreFichero = "fichero.txt";//nombre del fichero
            flujo_salida.writeUTF(nombreFichero);

            String error = flujo_entrada.readUTF();
            if (error.equals("")) {
                //lo almacenamos en el buffer
                BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero));
                
                String linea = flujo_entrada.readUTF();
                while (!linea.equals("Fin de la transmisión")) {
                    System.out.println(linea);
                    bw.write(linea + "\n");
                    linea = flujo_entrada.readUTF();
                }
                bw.close();
            } else {
                System.out.println(error);
            }

            // Cierro el socket
            sCliente.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}

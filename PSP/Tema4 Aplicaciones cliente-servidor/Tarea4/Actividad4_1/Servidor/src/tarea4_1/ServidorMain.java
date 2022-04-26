/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea4_1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Antonio
 */
class ServidorMain extends Thread {

    Socket skCliente;
    static final int Puerto = 2000;

    public ServidorMain(Socket sCliente) {

        skCliente = sCliente;
    }

    static final int puerto = 2000;
    
    public static void main(String[] args) {
        try {
            // Inicio el servidor en el puerto
            ServerSocket skServidor = new ServerSocket(Puerto);
            System.out.println("Escucho el puerto " + Puerto+"\n");
            while (true) {

                // Se conecta un cliente
                Socket skCliente = skServidor.accept();
                System.out.println("Cliente conectado");
// Atiendo al cliente mediante un thread
                new ServidorMain(skCliente).start();
            }
        } catch (Exception e) {;
        }

    }

    public void run() {
        
            try {
               
                System.out.println("Sirvo al cliente \n");
                //stream de entrada y salida de datos para comunicación
                DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());
                DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());

                String nombreFichero = flujo_entrada.readUTF();
                File fichero = new File(nombreFichero);

                if (fichero.exists()) {
                    //si existe el fichero, no enviar mensaje de error
                    flujo_salida.writeUTF("");
                    //obtenemos el br.readline
                    BufferedReader br = new BufferedReader(new FileReader(fichero));
                    String linea = br.readLine();
                    while (linea != null) {
                        flujo_salida.writeUTF(linea);
                        linea = br.readLine();
                    }
                    flujo_salida.writeUTF("Fin de la transmision de datos");
                    br.close();
                } else {
                    //no existe el fichero
                    flujo_salida.writeUTF("Error: No existe el fichero");
                }
                // Cierro el socket
                skCliente.close();

            } catch (Exception e) {
                System.out.println("error " + e);
            }

        }
    }


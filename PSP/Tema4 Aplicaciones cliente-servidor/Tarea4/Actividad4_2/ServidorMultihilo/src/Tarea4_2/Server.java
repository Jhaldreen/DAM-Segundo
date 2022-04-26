/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea4_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Antonio
 */
public class Server extends Thread {

    private Socket skCliente;

    public Server(Socket skCliente) {
        this.skCliente = skCliente;
    }

    public void run() {
        //con esto pruebo si funcionan los metodos
/*String [] archivo = Metodos.nombreArchivo(".");
        System.out.println("********"+archivo.length);*/
        try {

            // Creo los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());

            // ATENDER PETICIÓN DEL CLIENTE
            flujo_salida.writeUTF("Se ha conectado el cliente de forma correcta");
            String comando = "";
           
            int estado = 1;
            do {
                switch (estado) {
                    case 1:
                        flujo_salida.writeUTF("Introduce comando (ls/get/exit)");
                        comando = flujo_entrada.readUTF();//lee la opcion del cliente
                        if (comando.equals("ls")) {//si es ls
                            System.out.println("\tEl cliente quiere ver el contenido del directorio");
                            //cargo el directorio en el que estoy con el metodo que he creado en clase Metodos
                            String[] nombresArchivos = Metodos.cargarDirectorio(".");
                            flujo_salida.writeInt(nombresArchivos.length);
                            for (int i = 0; i < nombresArchivos.length; i++) {
                                flujo_salida.writeUTF(nombresArchivos[i]);
                            }
                            // Muestro el directorio
                           
                            estado=1;
                            break;
                        } else if (comando.equals("get")) {
                            
                            flujo_salida.writeUTF("Introduce el nombre del Archivo");
                            comando = flujo_entrada.readUTF();//lee la nombre del archivo
                            System.out.println("\tEl cliente quiere ver el nomnbre del archivo");
                            String[] nombresArchivos = Metodos.nombreArchivo(comando);
                            
                            flujo_salida.writeInt(nombresArchivos.length);
                            for (int i = 0; i < nombresArchivos.length; i++) {
                                flujo_salida.writeUTF(nombresArchivos[i]);
                            }
                            estado = 1;//volver al estado 1
                            
                            break;
                        } else if (comando.equals("exit")) {
                            estado = -1;
                        }
                        break;
                    case 3://voy a mostrar el archivo
                        flujo_salida.writeUTF("Introduce el nombre del archivo");
                        String fichero = flujo_entrada.readUTF();
                        // Muestor el fichero
                        estado = 1;
                        break;
                }

                if (comando.equals("exit")) {
                    estado = -1;
                }

            } while (estado != -1);

            // Se cierra la conexión
            skCliente.close();
            System.out.println("Cliente desconectado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea4_3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            int estado = 1;
            Scanner sc = new Scanner(System.in);

            Socket skCliente = new Socket(HOST, Puerto);
            // Creo los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in)); //nos permite leer desde el teclado
            System.out.println("Indique su nombre de usuario y contraseña.");
            System.out.println("Nombre de usuario: ");
            String usuario = teclado.readLine(); //leemos el nombre de usuario que ha metido el cliente por pantalla
            System.out.println("Contraseña:");
            String contraseña = teclado.readLine(); //leemos la contraseña que el usuario ha metido por pantalla
            flujo_salida.writeUTF(usuario);
            flujo_salida.writeUTF(contraseña);
            String mensaje = flujo_entrada.readUTF();
            if (mensaje.equals("Usuario no autorizado")) {
                System.out.println(mensaje);

            } else {

                System.out.println(mensaje);
                System.out.println(flujo_entrada.readUTF());
                // TAREAS QUE REALIZA EL CLIENTE

                System.out.println(flujo_entrada.readUTF());//conectado
                do {
                    String datos = flujo_entrada.readUTF();//recibo introduce archivo
                    System.out.println(datos);
                    String opcion = sc.nextLine();
                    flujo_salida.writeUTF(opcion);
                    switch (opcion) {
                        case "ls":

                            int n = flujo_entrada.readInt();//recive el numero de ficheros en el directorio
                            for (int i = 0; i < n; i++) {//bucle para listar las carpetas
                                System.out.println(flujo_entrada.readUTF());
                            }

                            break;
                        case "get":
                            //recibe las carpetas dentro del directorio escrito
                            System.out.println(flujo_entrada.readUTF());
                            String nombre = sc.nextLine();
                            flujo_salida.writeUTF(nombre);
                            int g = flujo_entrada.readInt();//numero de archivos totales
                            for (int i = 0; i < g; i++) {
                                System.out.println(flujo_entrada.readUTF());
                            }
                            break;
                        //IMPORTANTE poner case exit, de lo contrario salta la excepcion
                        case "exit":
                            System.exit(0);
                            break;
                    }
                    //para que no se cierre, tiene que ser distinto de -1
                    // de esta manera volvemos a empezar en el estado 1
                } while (estado != -1);

                skCliente.close();//cerramos el socket

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

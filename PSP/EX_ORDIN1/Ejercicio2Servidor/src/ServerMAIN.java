
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Antonio
 */
public class ServerMAIN {

    static final int puerto = 6060;

    public static void main(String[] args) {
//         System.setProperty("java.security.policy", 
//                "./server.policy");
//        System.setSecurityManager(new SecurityManager());
        //Genero el logger para almacenar los errores
        MyLogger log = new MyLogger();
        log.myLogger();

        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Escucho el puerto " + puerto);
            while (true) {//Bucle infinito de atención a las conexiones
                //Sentencia bloqueante, queda permanentemente a la espera de una petición
                Socket socket = servidor.accept();
                System.out.println("*******Conectado con el cliente******");
                HiloServidor hilo = new HiloServidor(socket);//instanciamos la clase HiloServidor
                //se inicia el servicio en un hilo con el socket de cliente 
                //específico para ese hilo, y se vuelve en el while hasta el accept
                hilo.start();

            }
        } catch (IOException ex) {
            Logger.getLogger(ServerMAIN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

class HiloServidor extends Thread {


    private Socket skCliente;
    private Scanner teclado; //para introducir datos por teclado

    public HiloServidor(Socket skCliente) {
        this.skCliente = skCliente;
    }

    public void run() {

        try {
            DataInputStream flujo_entrada = null;
            // Creo los flujos de entrada y salida
            flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());
            // ATENDER PETICIÓN DEL CLIENTE
            flujo_salida.writeUTF("Bienvenido al Servidor");//mando al cliente
            System.out.println(flujo_entrada.readUTF());//recibo del cliente saludo

            //recivo el mensaje
            System.out.println(flujo_entrada.readUTF());
            //Guardar(flujo_entrada.readUTF());//guardamos los mesnajes recibidos
            System.out.println("El mensaje es correcto");
            flujo_salida.writeUTF("Gracias por su visita");

            // Se cierra la conexiones
            flujo_entrada.close();
            flujo_salida.close();
            skCliente.close();

        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Adios");

    }

    public static void Guardar(String nombre) {//metodo para guardar los datos del cliente
        File file = new File("d:/psp/servidor.txt");//ruta del archivo

        FileWriter fr = null;
        BufferedWriter br = null;
        try {

            fr = new FileWriter(file, true);//true para seguir añadiendo nombres
            br = new BufferedWriter(fr);
            //cerramos conexiones
            br.write(nombre);
            br.close();
            fr.close();

        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}

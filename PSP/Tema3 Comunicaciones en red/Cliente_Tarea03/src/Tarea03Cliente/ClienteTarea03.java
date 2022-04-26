package Tarea03Cliente;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Antonio
 */
public class ClienteTarea03 {

    static final String Host = "localhost";
    static final int Puerto = 2223;
    DataInputStream flujo_entrada = null;//entrada de datos
    File fichero = null;
    PrintWriter escribe = null;//para escribir el fichero
    //para hacer la aplicacion mas creible le ponemos que escriba nombre del fihero
    // Scanner sc = new Scanner(System.in);

    public ClienteTarea03() {

        try {
            Socket sCliente = new Socket(Host, Puerto);
            //entrada de datos
            flujo_entrada = new DataInputStream(sCliente.getInputStream());
            //le pongo un Scanner de teclado para pedir el fichero por ejemplo            
//                  System.out.println("Introduzca nombre del fichero");
//                      String nombre = sc.nextLine();
//            Si queremos hacerlo con el scanner sustitumos las comillas de fichero
//            por la variable String en este caso nombre
//            lo lógico es que el nombre del fichero se llame igual que el del servidor
            fichero = new File("FicheroTarea3.txt");//darle nombre al fichero
            //recibe el fichero con read UTF para que salga por pantalla el nombre del
            //del fichero del servidor
             System.out.println("Nombre del fichero recibido " + flujo_entrada.readUTF()+"\n");
            escribe = new PrintWriter(fichero);//para escribir el fichero
            String linea = new String();//recibimos la informacion
           
            while (!linea.equalsIgnoreCase("Fin de transmisión de los datos")) {
                linea = flujo_entrada.readUTF();//leemos el fichero del servidor
                System.out.println(linea);//imprimimos lo leido del servidor
                escribe.println(linea);//escribimos lo leido del servidor
            }
            
            escribe.close();//cerramos el fichero
            sCliente.close();//cerramos el socket
        } catch (Exception e) { 
            System.out.println(e.getMessage());
        }

    }

}

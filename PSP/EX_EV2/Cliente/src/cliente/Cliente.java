
package cliente;


import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    
    static final String HOST = "127.0.0.1";
    static final int Puerto=5555;       //Host y puerto
       
        public Cliente( )  {
        try{

            Socket sCliente = new Socket( HOST , Puerto );  //Conexión en local, a través del puerto indicado

            DataOutputStream f_salida= new DataOutputStream( sCliente.getOutputStream() );
            DataInputStream f_entrada = new DataInputStream( sCliente.getInputStream() );    //Abrimos recursos in y out
     
            String msj="";
            String llegada;
            Scanner teclado=new Scanner(System.in);
            
            
            
            llegada = f_entrada.readUTF(); //recibimos hola
            System.out.println(llegada); //mostramos mensaje.

            do {
                //recibimos y almacenamos mensaje del servidor para introducir el comando
                llegada = f_entrada.readUTF();
                System.out.println(llegada); //mostramos mensaje.

                
                msj = teclado.next();
                f_salida.writeUTF(msj);

                //recibimos y mostramos la respuesta del servidor tras enviar el comando.
                llegada = f_entrada.readUTF();
                System.out.println(llegada);

                if (llegada.contains("Mensajes")) {

                    do {
                        llegada = f_entrada.readUTF();
                        System.out.println(llegada);

                    } while (!llegada.equals("EOF"));

                }
                
            } while (!(msj.equals("Salir")));

            System.out.println("Salgo del bucle");

 
            System.out.println("Fin de la conexión");

            f_salida.close();
            f_entrada.close();
            sCliente.close();
        } catch (IOException ex) {
        System.err.println("Error de I/O");
        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                
        public static void main(String[] args) {
        new Cliente();                          //generamos objeto Cliente
    }
}

    

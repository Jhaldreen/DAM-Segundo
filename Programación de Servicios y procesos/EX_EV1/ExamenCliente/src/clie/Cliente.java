package clie;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    
    static final String HOST = "127.0.0.1";
    static final int Puerto=5000;       //Host y puerto
       
        public Cliente( ) {
            
        try{
            
            Socket sCliente = new Socket( HOST , Puerto );  //Conexión en local, a través del puerto indicado
            OutputStream aux = sCliente.getOutputStream();
            DataOutputStream f_salida= new DataOutputStream( aux );
            InputStream aux2 =sCliente.getInputStream();
            DataInputStream f_entrada = new DataInputStream( aux2 );    //Abrimos recursos in y out
            String mensaje; 
            Scanner teclado = new Scanner(System.in);//entrda de teclado para las preguntas
            
            
            mensaje=f_entrada.readUTF();  //recibimos bienvenida
            
            System.out.println(mensaje);
            
            while (true){       //bucle pra preguntas
            
                System.out.println("Escribo mi pregunta");
                mensaje=teclado.nextLine();
                f_salida.writeUTF(mensaje);
                
                if (mensaje.equals("FIN")){ // salimos del while
                    
                break;
                }
                
                mensaje=f_entrada.readUTF(); //leemos lo que nos llega dependiendo de la pregunta que hemos hecho, y volvemos al comienzo del while
                System.out.println(mensaje);
               }
            
           

        //Han finalizado las operaciones, cerrando recursos.

        
        f_entrada.close();
        f_salida.close();       
        aux.close();     
        aux2.close();
        sCliente.close();
        
        }  
        
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}

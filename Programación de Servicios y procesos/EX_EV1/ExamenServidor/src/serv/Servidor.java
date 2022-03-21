package serv;

import java.io.* ;
import java.net.* ;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    
     static final int Puerto=5000;
     String mensaje="Bienvenido al servidor de preguntas";
     
     
    public Servidor(){
        
         try {
             ServerSocket skServidor = new ServerSocket(Puerto);
             
             Socket sCliente = skServidor.accept(); //a la escucha en el puerto 5000
             InputStream aux = sCliente.getInputStream();
             OutputStream aux2= sCliente.getOutputStream();
             DataInputStream f_entrada = new DataInputStream( aux );
             DataOutputStream f_salida= new DataOutputStream( aux2 );  //recursos in out
             
             f_salida.writeUTF(mensaje);
             
             
             
             while(true){
                 
                mensaje=f_entrada.readUTF();
                
                if(mensaje.equals("Como te llamas")){       //Con el while esperamos en bucle a las preguntas

                    mensaje="Me llamo Jorge";
                    f_salida.writeUTF(mensaje);
                }

                else if (mensaje.equals("Cuantos anios tienes")){
                    mensaje="Tengo 36 anios";
                    f_salida.writeUTF(mensaje);
                }
                else if(mensaje.equals("FIN")){
                    break;  //cuando reciba fin rompemos el bucle y acabamos
                }

                else{
                   mensaje="No he entendido la pregunta";
                    f_salida.writeUTF(mensaje);
                }
            }
             
             f_entrada.close(); //cerramos recursos
             aux.close();
             f_salida.close();
             aux2.close();
             sCliente.close();
             skServidor.close();
         } catch (IOException ex) {
             Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
        
}


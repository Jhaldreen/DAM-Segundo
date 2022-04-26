
package servidor;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static servidor.Servidor.mensajes;


public class Servidor extends Thread {  //Atención a clientes mediante multi-hilo
    
    Socket socCliente;
    public static ArrayList<String[]> mensajes=new ArrayList();       //Arraylist de arrays tipo String para los mensajes, static para que sea compartido
    
    private static boolean libre = true;  //empezamos en libre
                            
    Servidor(Socket sc){        //Como parámetro de entrada del constructor, se requiere el socket de cada cliente
    
        this.socCliente=sc;
    }
    
    public static void main(String[] args) throws IOException {        //el main solo para conectarse con los clientes, las acciones al run
        
        ServerSocket socServidor = new ServerSocket(5555);       //definimos el serversocket en el puerto indicado, común para todos los hilos

        while (true) {  //Bucle infinito de atención a las conexiones
            System.out.println("A la espera de clientes");      //mensaje interno del server para ver el estado
            Socket socCliente = socServidor.accept();        //Sentencia bloqueante, queda permanentemente a la espera de una petición

            System.out.println("Atendiendo al cliente");
            new Servidor(socCliente).start();     //se inicia el servicio en un hilo con el socket de cliente específico para ese hilo, y se vuelve en el while hasta el accept
        }
    }
        
    public void run(){      //cuando se crea un hilo de servidor, se invoca al método run

        try {
            procesaPeticion(socCliente);
            socCliente.close(); //una vez finalizadas, se cierra el socket de cliente


        } catch (IOException ex) {
            System.err.println("error de I/O");
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    System.out.println("cliente atendido");      //mensaje tras haber sido atendido el cliente
   
    }
    
           
    private void procesaPeticion(Socket sc) throws IOException, InterruptedException{
   
        DataInputStream f_entrada = new DataInputStream(sc.getInputStream());       //Preparamos flujos de entrada y salida
        DataOutputStream f_salida = new DataOutputStream(sc.getOutputStream());
        String comando="";  //variables que llevará la orden
        
        f_salida.writeUTF("HOLA");      //Mandamos mensaje al cliente
        
        do {    //mientras no nos llegue el mensaje Salir estaremos pidiendo comandos
                f_salida.writeUTF("Introduzca comando");      //Pedimos comando      
                comando=f_entrada.readUTF();
                String[] partes=comando.split("#");
           
                    if(partes[1].equals("escribir")){    //nos fijamos en el comienzo de los comandos para realizar las acciones
                         System.out.println("escribiendo");
                    escribeMensaje(comando);
                    break;
                    }else if(partes[1].equals("leer")){
                         System.out.println("leyendo");
                    String[] mensajes=leeMensaje(comando);
                    String[] partes2=comando.split("#");
                    f_salida.writeUTF("Mensajes de "+partes[2]+":");
                    
                    for(int x=0;x<mensajes.length;x++){
                        f_salida.writeUTF(mensajes[x]);
                    }  
                    f_salida.writeUTF("EOF");
                    break;
                    }else if(comando.contains("Salir")){
                    
                    break;} else break;
        
        }while(!comando.equals("Salir"));
         f_salida.writeUTF("Adiós");
    }    
    

    public synchronized void escribeMensaje(String com) throws IOException, InterruptedException {
        while(!libre) wait(); //esperamos mientras la booleana esté a false
      
            libre=false;  //cambia estado, el hilo se reserva el arraylist para él
            BufferedWriter writer = new BufferedWriter(new FileWriter("D:/servidor.txt", true)); //Se va escribiendo el contenido en un archivo, no sobreescribe
            String[] partes=com.split("#");
            mensajes.add(partes);       //insertamos en el arraylist estatico
            for(int x=1;x<3;x++){
                writer.write(partes[x]);    //escribimos solamente el user y el mensaje, por eso x=1
            }
            libre=true;
            notifyAll();        //liberamos método y notificamos
    }
    

public synchronized String[] leeMensaje(String com) throws IOException, InterruptedException {
        while(!libre) wait(); //esperamos mientras la booleana esté a false
        
            libre=false;  //cambia estado, el hilo se reserva el arraylist para él
            String partes[]=com.split("#");
            String[] msjs=null;
            String nombre=partes[0];  //Nos quedamos con el nombre
            for(int x=0;x<mensajes.size();x++){     //por cada registro en el arraylist, que es un array
                String[] mensaje =mensajes.get(x); //sacamos la posición a un array de strings
                if(mensaje[1].equals(nombre)){      //si el nombre, que está en la posición 1, coincide con el nombre, seguimos en el if
                msjs[x]= mensaje[2];  //almacenamos en array string cada mensaje que nos llegue 
                }  
            }
            libre=true;
            notifyAll();
            
        return msjs;
    }
}
 


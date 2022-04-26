
package Tarea03;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;

import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Antonio
 */
public class ServidorTarea03 {

    static final int Puerto = 2223;
    // Apertura del fichero y creacion de BufferedReader para poder
    // hacer una lectura comoda (disponer del metodo readLine()).
    File archivo = new File("FicheroTarea3.txt");//nombramos el fichero
    FileReader fr;
    BufferedReader br;//almacen en el buffer
    //DataInputStream flujo_entrada = null;
    DataOutputStream flujo_salida = null;//Stream para la salida de datos

    public ServidorTarea03() {

        try {
            // Inicio la escucha del servidor en un determinado puerto
            ServerSocket skServidor;
            skServidor = new ServerSocket(Puerto);
            System.out.println("Escucho el puerto " + Puerto);
            
            // Espero a que se conecte un cliente y creo un nuevo socket para el cliente
            Socket sCliente = skServidor.accept();
            //sacamos los datos por el flujo de salida mediante Socket
            flujo_salida = new DataOutputStream(sCliente.getOutputStream());
            fr = new FileReader(archivo);//leemos el archivos
            br = new BufferedReader(fr);
            
            //condicion para que se mande el fichero o archivo al cliente cuando lo pida
            //si no es el nombre correcto dará error
            
            System.out.println("Empezando la transmisión de datos");
            flujo_salida.writeUTF(archivo.getName());//lo sacamos hacia el cliente

            // recorrermos fichero
            String linea = new String();
            while ((linea = br.readLine()) != null) {
                flujo_salida.writeUTF(linea);//enviamos los datos  
                
            }
          
           flujo_salida.writeUTF("Fin de transmisión");
            sCliente.close();//cerramos el socket
            System.out.println("fin de la transmision");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

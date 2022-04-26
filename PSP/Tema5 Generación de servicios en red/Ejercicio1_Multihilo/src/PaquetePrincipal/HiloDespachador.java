
package PaquetePrincipal;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Antonio
 */
public class HiloDespachador extends Thread {// extension de la clase Thread

    private Socket socketCliente;
    //el constructor almacena el socketCliente que recibe de una variable local
    public HiloDespachador(Socket socketCliente) {//constructor

        this.socketCliente = socketCliente;
    }
    //utilizamos el metodo run para tramitar la respuesta mediante variable local socketCliente
    public void run() {

        try {
            //tramita la petición por el socketCliente     
            ServidorHTTP.procesaPeticion(socketCliente);
            //cierra la conexión entrante
            socketCliente.close();
            System.out.println("cliente atendido");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}

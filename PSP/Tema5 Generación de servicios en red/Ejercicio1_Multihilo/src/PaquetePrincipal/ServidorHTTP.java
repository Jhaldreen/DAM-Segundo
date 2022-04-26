/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaquetePrincipal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Antonio
 */
class ServidorHTTP {

    private static Socket socCliente;
    private static HiloDespachador hilo;

    /**
     * **************************************************************************
     * procedimiento principal que asigna a cada petición entrante un socket
     * cliente, por donde se enviará la respuesta una vez procesada
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        try {

            ServerSocket socServidor = new ServerSocket(8066);
            imprimeDisponible();
            Socket socCliente;

            //ante una petición entrante, procesa la petición por el socket cliente
            //por donde la recibe
            while (true) {
                /*a la espera de peticiones cuando reciba una la aceptará y le
                asignará un socket cliente para  enviarle respuesta. Pero en lugar 
                de atenderla él mismo, el hilo principal creará un nuevo hilo 
                para que la despache por el socket cliente que le asignó. 
                De esta forma, podrá seguir a la espera de nuevas peticiones.*/
                socCliente = socServidor.accept();
                //atiendo un cliente
                System.out.println("Atendiendo al cliente " + socCliente.toString());
                //crea un nuevo hilo para despacharla por el socketCliente que le asignó
                HiloDespachador hilo = new HiloDespachador(socCliente);
                //inicia el hilo
                hilo.start();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
        
    static void procesaPeticion(Socket socketCliente) throws IOException {
        //variables locales
        String peticion;
        String html;

        //Flujo de entrada
        InputStreamReader inSR = new InputStreamReader(
                socketCliente.getInputStream());
        //espacio en memoria para la entrada de peticiones
        BufferedReader bufLeer = new BufferedReader(inSR);

        //objeto de java.io que entre otras características, permite escribir 
        //'línea a línea' en un flujo de salida
        PrintWriter printWriter = new PrintWriter(
                socketCliente.getOutputStream(), true);

        //mensaje petición cliente
        peticion = bufLeer.readLine();

        //para compactar la petición y facilitar así su análisis, suprimimos todos 
        //los espacios en blanco que contenga
        peticion = peticion.replaceAll(" ", "");

        //si realmente se trata de una petición 'GET' (que es la única que vamos a
        //implementar en nuestro Servidor)
        if (peticion.startsWith("GET")) {
            //extrae la subcadena entre 'GET' y 'HTTP/1.1'
            peticion = peticion.substring(3, peticion.lastIndexOf("HTTP"));

            //si corresponde a la página de inicio
            if (peticion.length() == 0 || peticion.equals("/")) {
                //sirve la página
                html = Paginas.html_index;
                printWriter.println(Mensajes.lineaInicial_OK);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Content-Length: " + html.length() + 1);
                printWriter.println("\n");
                printWriter.println(html);
            } //si corresponde a la página del Quijote
            else if (peticion.equals("/quijote")) {
                //sirve la página
                html = Paginas.html_quijote;
                printWriter.println(Mensajes.lineaInicial_OK);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Content-Length: " + html.length() + 1);
                printWriter.println("\n");
                printWriter.println(html);
            } //en cualquier otro caso
            else {
                //sirve la página
                html = Paginas.html_noEncontrado;
                printWriter.println(Mensajes.lineaInicial_NotFound);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Content-Length: " + html.length() + 1);
                printWriter.println("\n");
                printWriter.println(html);
            }

        }
    }

    /**
     * **************************************************************************
     * muestra un mensaje en la Salida que confirma el arranque, y da algunas
     * indicaciones posteriores
     */
    private static void imprimeDisponible() {

        System.out.println("El Servidor WEB se está ejecutando y permanece a la "
                + "escucha por el puerto 8066.\nEscribe en la barra de direcciones "
                + "de tu explorador preferido:\n\nhttp://localhost:8066\npara "
                + "solicitar la página de bienvenida\n\nhttp://localhost:8066/"
                + "quijote\n para solicitar una página del Quijote,\n\nhttp://"
                + "localhost:8066/q\n para simular un error");
    }
}

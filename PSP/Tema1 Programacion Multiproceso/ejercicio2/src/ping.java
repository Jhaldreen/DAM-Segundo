
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Antonio
 */
public class ping {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        String ip = args[1];
        int v1 = Integer.parseInt(ip);// paso a entero el argumento 2
        for (int i = 0; i < v1; i++) {

            Runtime rt = Runtime.getRuntime();
            Process p = null;
            //Mediante substring obtenemos el cambio del ultimo numero de la IP
            String ip0 = args[0];
            ip0 = ip0.substring(0, 12);

            String ip1 = args[0];
            ip1 = ip1.substring(12);

            int v2 = Integer.parseInt(ip1);//pasamos a un entero
            v2 = v2 + i;
            String ip3 = String.valueOf(v2);//conversion a cadena
            ip3 = ip0 + ip3;

            String[] comando = {"CMD", "/c", "ping", ip3};

            try {

                p = new ProcessBuilder(comando).start();
                System.out.println("\n IP:" + ip3);
            } catch (IOException ex) {
                System.out.println("Error al ejecutar el comando" + comando);
                System.exit(-1);
            }

            InputStream is;//recoger como entrada la salida del proceso ejecutado
            InputStreamReader isr;//reader para leer el input stream
            BufferedReader bfr;// para que salga lo obtenido

            is = p.getInputStream(); // Lee el stream de salida del proceso
            //para poder usar el método readLine() necesito un objeto BufferedReder
            isr = new InputStreamReader(is);
            bfr = new BufferedReader(isr);

            try {
                //
                BufferedWriter bw = new BufferedWriter(new FileWriter(args[2]));

                String line = bfr.readLine(); //leo la primera linea

                while (line != null) { //leo hasta el final de la salida del bufferReader
                    System.out.println(line);
                    line = bfr.readLine();
                    bw.write(line); //se escribe el fichero 
                    bw.newLine();
                    bw.flush();//si se llena el buffer vacia y ejecuta los datos

                }
                bw.close();//cerrar la escritura
            } catch (IOException ex) {
                ex.printStackTrace();//si hay error de E/S
            }

            try {
                int retorno = p.waitFor();
                System.out.println("\n El valor del proceso es " + retorno);
            } catch (InterruptedException ex) {
                System.out.println("error");
            }
            bfr.close();

        }

    }

}


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;


public class MyLogger {
    
    
     public  void myLogger() {

        //Buscar o crear el logger que queremos utilizar
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("MyLogger");//la clase donde se trabaja
        FileHandler fh;//importar la librería FileHandler

        try {
            //Una vez definido el logger debemos asociarlo a un fichero log
            fh = new FileHandler("registros.log", true);
            logger.addHandler(fh);
            //añadimo el nivel de seguridad 
            logger.setLevel(Level.WARNING);

            // si queremos visualizar los mensajes de log por pantalla 
            logger.setUseParentHandlers(false);//así no se ven con true se ven
            //establecer el formato del fichero 
            SimpleFormatter formatter = new SimpleFormatter();

            fh.setFormatter(formatter);

            //añado un mensaje al log
            logger.log(Level.WARNING, "Registro de fallos");

        } catch (IOException ex) {//problemas con la apertura de ficheros
            ex.printStackTrace();
            //si existete security manager y si no hay permisos de Loggin
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }
}


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Antonio
 */
public class LoginPass {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        
        //policyTool 
        
        
//         System.setProperty("java.security.policy", 
//                             "./policy2.policy");
//        System.setSecurityManager(new SecurityManager());

        MyLogger log = new MyLogger();
        log.myLogger();
        ValidarEntrada();
    }

    public static void ValidarEntrada() {

        String nombre = new String();
        String nom_fichero = new String();
        Pattern pat = null;
        Matcher mat = null;
        boolean valido = true;

        // para leer del teclado
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                //añado el boolenao, por que salta el nombre todo el rato
                valido = true;
                System.out.println("Introduce tu nombre (6 caracteres en minuscula):");
                nombre = reader.readLine();
                //patron para  caracteres 
                pat = Pattern.compile("[a-z]{6}");
              //Le pasamos al evaluador de expresiones el texto a comprobar
                mat = pat.matcher(nombre);
                //
                if (!mat.find()) {
                    System.out.println("El nombre no es valido prueba otra vez"+ Logger.getLogger(nombre));
                    valido = false;
                }else{System.out.println("El nombre es valido");
                valido = true;
                }
            } while (!valido);
            System.out.println("Introduce nombre de un fichero "
                    + "(8 caracteres y un extension de 3 ejemplo fichero.txt):");
            nom_fichero = reader.readLine();
            //fichero de texto con 8 caracteres y 3 caracteres para la extension del archivo
            pat = Pattern.compile("[a-zA-Z]{1,8}.[a-zA-Z]{3}");
            mat = pat.matcher(nom_fichero);
            LeerFichero(nom_fichero);

            if (!mat.find()) {
                System.out.println("Nombre de fichero no válido  " + nom_fichero);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void LeerFichero(String fichero) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(fichero));
            //leemos la linea y la printamos 
            String linea = "";
            System.out.println("Leo el contendio del fichero. \n");
            
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close(); //cierro flujo lectura nomfichero
        } catch (FileNotFoundException ex) {//no encuentra el fichero
            Logger.getLogger(LoginPass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginPass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}

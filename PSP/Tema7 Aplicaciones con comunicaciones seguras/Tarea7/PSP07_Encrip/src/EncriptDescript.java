
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Antonio
 */
public class EncriptDescript {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KeyGenerator keyGen = null;
        SecretKey secretKey = null;
        SecretKeySpec skeySpec = null;
        File ficheroEncriptado = new File("miFichero");
        File keyFile = new File("miKey");

        try {
            //primero genero una clave segura aleatorio usando AES
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            secretKey = keyGen.generateKey();
            byte[] raw256 = secretKey.getEncoded();
            byte[] raw192= Arrays.copyOf(raw256,192);
            skeySpec = new SecretKeySpec(raw256, "AES");

            String textoACifrar = "Este es un texto de prueba para cifrar y luego descifrar sacando "
                    + "este mismo texto por pantalla";
            
            cifrar(skeySpec, ficheroEncriptado, keyFile, textoACifrar);
            String contenidoDescifrado = descifrar(skeySpec, ficheroEncriptado, keyFile, textoACifrar);

            System.out.println(contenidoDescifrado);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
       
    public static void cifrar(SecretKeySpec skeySpec, File ficheroEncriptado, File keyFile, String texto)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, 
            BadPaddingException, IOException {

        //creo cipher para encriptar
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        //Encriptamos los datos
        byte[] encrypted = cipher.doFinal(texto.getBytes());
        
        //guardo la información encriptada en el fichero
        FileOutputStream fos = new FileOutputStream(ficheroEncriptado);
        fos.write(encrypted);
        fos.close();

        //guardo the cipher settings
        byte[] encodedKeySpec = skeySpec.getEncoded();
        FileOutputStream eksos = new FileOutputStream(keyFile);
        eksos.write(encodedKeySpec);
        eksos.close();
    }

    public static String descifrar(SecretKeySpec skeySpec, File ficheroEncriptado, File keyFile, String texto) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //Ahora a la inversa leo los datos encriptados
        FileInputStream fis = new FileInputStream(ficheroEncriptado);
        byte[] temp = new byte[8192];
        int bytesRead = fis.read(temp);
        byte[] data = new byte[bytesRead];
        System.arraycopy(temp, 0, data, 0, bytesRead);

        // Read the cipher settings
        FileInputStream eksis = new FileInputStream(keyFile);
        bytesRead = eksis.read(temp);
        byte[] encodedKeySpec = new byte[bytesRead];
        System.arraycopy(temp, 0, encodedKeySpec, 0, bytesRead);

        // Recreate the secret/symmetric key
        skeySpec = new SecretKeySpec(encodedKeySpec, "AES");

        // Create the cipher for encrypting
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        // Decrypt the data
        byte[] decrypted = cipher.doFinal(data);

        return new String(decrypted);
    }

}

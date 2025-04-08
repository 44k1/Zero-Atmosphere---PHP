import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Base64;


public class DecryptOperation {

    // Método para leer el archivo y desencriptarlo usando una clave
    public static void decrypt(String password, String ficheroADescifrar) {
        try {
            // Leer el contenido del archivo
            String encryptedText = new String(Files.readAllBytes(Paths.get(ficheroADescifrar)));

            // Llamar al método de desencriptado
            String decryptedText = decryptText(encryptedText, password);

            // Mostrar el resultado desencriptado
            
            if (decryptedText!= null){ // != null, ya que si no se desencripta con exito retorna null, entonces lo evitamos..
                System.out.println("<---- FICHERO DESENCRIPTADO ----->");
                System.out.println(decryptedText);
            }
            
        } catch (NoSuchFileException e) {
            System.out.println("Error --> El archivo no existe. Verifica la ruta y el nombre.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public static String decryptParaGUI(String password, String ficheroADescifrar) {
        try {
            // Leer el contenido del archivo
            String encryptedText = new String(Files.readAllBytes(Paths.get(ficheroADescifrar)));

            // Llamar al método de desencriptado
            String decryptedText = decryptText(encryptedText, password);

            // Mostrar el resultado desencriptado
            if (decryptedText!= null){ // != null, ya que si no se desencripta con exito retorna null, entonces lo evitamos..
                System.out.println("<---- FICHERO DESENCRIPTADO ----->");
                return decryptedText;
            }
            
        } catch (NoSuchFileException e) {
            System.out.println("Error --> El archivo no existe. Verifica la ruta y el nombre.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return null;
    }

    // Método para desencriptar el texto usando la clave
    public static String decryptText(String encryptedText, String password) {
        try {
            // Generar una clave a partir de la contraseña
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");

            // Inicializar el cifrador AES
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            // Desencriptar el texto
            byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

            return new String(decryptedText);
        } catch (IllegalBlockSizeException e){
            System.out.println("ERROR, el valor introducido no es multiplo de 16 o no esta codeado en BASE64 ");
        } catch (BadPaddingException e){
            System.out.println("ERROR --> La key introducida no coincide con la utilizada para el cifrado.");
        }catch (Exception e) {
            System.out.println("ERROR -> " + e);;
        } 
        return null;
    }

}

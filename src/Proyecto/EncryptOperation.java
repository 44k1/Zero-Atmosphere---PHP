import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
public class EncryptOperation {
    public static void saveToFile(String filename, String data) {
        try {
            Files.write(Paths.get(filename), data.getBytes());
            System.out.println("Resultado guardado en " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para encriptar un texto usando una clave
    public static String encrypt(String text, String password) {
        try {
            // Generar una clave a partir de la contraseña (En AES)
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");

            // Inicializar el cifrador AES
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, key);

            // Encriptar el texto
            byte[] encryptedText = cipher.doFinal(text.getBytes());
            
            // Convertir a Base64 para almacenar en un archivo (Para facilitar su guardado con un formato predefinido)
            return Base64.getEncoder().encodeToString(encryptedText);
        } catch (Exception e) {
            System.out.println("ERROR -> " +e);
        }
        return null;
    }

  
}

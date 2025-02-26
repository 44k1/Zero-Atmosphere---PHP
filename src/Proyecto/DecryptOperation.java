import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class DecryptOperation {
    
    // Método para leer el archivo y desencriptarlo usando una clave
    public static void decrypt(String password) {
        try {
            // Leer el contenido del archivo
            String encryptedText = new String(Files.readAllBytes(Paths.get("costesOperacion.txt")));

            // Llamar al método de desencriptado
            String decryptedText = decryptText(encryptedText, password);

            // Mostrar el resultado desencriptado
            System.out.println("---- OPERACION DESENCRIPTADA -----");
            System.out.println(decryptedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserSystem {
    private static final String FILE_NAME = "UserDb.txt";
    static int contador = getLastID(FILE_NAME);
    public static boolean register(String username, String password, String key) {
        if (userExists(username)) {
            System.out.println("El usuario ya existe. Registro fallido.");
            return false;
        }
        String encryptedPassword = password;
        /*String encryptedPassword = EncryptOperation.encrypt(password, key);
        if (encryptedPassword == null) {
            System.out.println("Error al cifrar la contraseña.");
            return false;
        }*/
        String userData = contador+1 +","+username + "," + encryptedPassword + "," + key + "\n";
        saveToFile(FILE_NAME, userData);
        return true;
    }
    
    public static boolean login(String username, String password) {
        try {
            
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[1].equals(username)) {
                    String key = parts[3];
                    String decryptedPassword = DecryptOperation.decryptText(parts[2], key);
                    if (decryptedPassword != null && decryptedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer la base de datos de usuarios.");
        }
        return false;
    }
    
    public static boolean userExists(String username) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[1].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo verificar la existencia del usuario.");
        }
        return false;
    }

    
    public static int getLastID(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            int lastID = 0;

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        if (id > lastID) {
                            lastID = id;
                        }
                    } catch (NumberFormatException e) {
                        // Ignorar líneas con formato incorrecto
                    }
                }
            }
            return lastID;
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Indica error al leer el archivo
        }
    }
    private static void saveToFile(String filename, String data) {
        try {
            Files.write(Paths.get(filename), data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Usuario guardado en " + filename);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos de usuario.");
            e.printStackTrace();
        }
    }

    public static String getPassSec(Scanner sc, String key){
        String passwdOperacion;
        boolean exit = false;

        do {
            System.out.println("Introduzca una contraseña de 16/24/32 caracteres: ");
            Console console = System.console();

            if (console != null) {
                // Si la consola está disponible, usar readPassword() para ocultar la entrada
                char[] passwordArray = console.readPassword();
                passwdOperacion = new String(passwordArray);
                Arrays.fill(passwordArray, ' '); // Borra el array por seguridad
            } else {
                // Si no hay consola (ejemplo: en un IDE), usar Scanner
                passwdOperacion = sc.nextLine();
            }

            // Validar la longitud de la clave
            if (passwdOperacion.length() != 16 && passwdOperacion.length() != 24 && passwdOperacion.length() != 32) {
                System.out.println("Error: La contraseña debe tener 16, 24 o 32 caracteres. Inténtelo de nuevo.");
            } else {
                exit = true;
            }

        } while (!exit);

        return EncryptOperation.encrypt(passwdOperacion, key);
    }
}
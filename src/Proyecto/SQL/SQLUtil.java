
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;




public class SQLUtil {
    static Scanner sc = new Scanner(System.in);
    public static final String[] fichas = {
            "src\\Proyecto\\SQL\\ficha1\\mov_tierra.dat", "src\\Proyecto\\SQL\\ficha2\\martillo.dat",
            "src\\Proyecto\\SQL\\ficha4\\cibercompresor.dat"
            // Ficha1 //Ficha2 //Ficha4
    };
    static int filas = 3;
    static int columnas = 10;
    private static Maquinaria[][] maquinaria = new Maquinaria[filas][columnas];

    public static void cargarMatriz() {
        for (int i = 0; i < filas; i++) {
            File f = new File(fichas[i]);

            if (!f.exists()) {
                System.err.println("Error: No se encontró el archivo " + fichas[i]);
                continue; // Salta a la siguiente iteración si el archivo no existe
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                for (int j = 0; j < columnas; j++) {
                    maquinaria[i][j] = (Maquinaria) ois.readObject();

                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al leer el archivo " + fichas[i] + ": " + e.getMessage());
            }
        }
    }

    

    public static void main(String[] args) {
        cargarMatriz();
        mostrarMaquinaria();
        actualizacionSQL();
    }

    public static void mostrarMaquinaria() {
        System.out.println("\nDatos en maquinaria:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (maquinaria[i][j] == null) {
                    continue;
                }
                
                Maquinaria datos = maquinaria[i][j]; // Extraemos el objeto Maquinaria
                
                switch (i) {
                    case 0: // mov_tierra.dat
                    System.out.printf(datos.toString());
                    System.out.println("");
                        break;
                    case 1: // martillo.dat
                    System.out.printf(datos.toString());
                    System.out.println("");
                        break;
                    case 2: // cibercompresor.dat
                        System.out.printf(datos.toString());
                        System.out.println("");     
                        break;
                }
            }
        }
    }
    

    public static void actualizacionSQL() {
        // Establecer la conexión con la base de datos
        String url = "jdbc:mysql://localhost:3306/atmosferazero";  // Base de datos 'atmosferazero'
        String user = "root";
        String password = "mysql";


        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Recorrer los archivos en el array 'fichas'
            for (int i = 0; i < fichas.length; i++) {
                // Leer el archivo correspondiente
                File archivo = new File(fichas[i]);
                String tabla = determinarTabla(fichas[i]); // Obtener la tabla correspondiente
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                    int j = 0;
                    while (true) {
                        try {
                            // Leer el objeto de maquinaria desde el archivo
                            Maquinaria obj = (Maquinaria) ois.readObject();
                            // Asignar el objeto en la matriz
                            maquinaria[i][j] = obj;
                            // Insertar el objeto serializado en la base de datos
                            insertarObjetoSerializado(obj, conn, tabla);
                            j++;
                        } catch (EOFException e) {
                            break;  // Fin del archivo
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para determinar la tabla correspondiente según el archivo
    private static String determinarTabla(String rutaArchivo) {
        if (rutaArchivo.contains("mov_tierra")) {
            return "ficha1";
        } else if (rutaArchivo.contains("martillo")) {
            return "ficha2";
        } else if (rutaArchivo.contains("cibercompresor")) {
            return "ficha4";
        }
        return null; // Default en caso de no encontrar coincidencia
    }

    // Método para insertar el objeto serializado en la base de datos
    private static void insertarObjetoSerializado(Maquinaria obj, Connection conn, String tabla) {
        // SQL para insertar el objeto serializado en la base de datos
        String sql = "INSERT INTO " + tabla + " (objeto_serializado) VALUES (?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Serializar el objeto
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            byte[] objBytes = baos.toByteArray();
            
            // Insertar el objeto serializado como BLOB
            stmt.setBytes(1, objBytes);
            
            stmt.executeUpdate();
            System.out.println("Insertado en tabla: " + tabla);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


}
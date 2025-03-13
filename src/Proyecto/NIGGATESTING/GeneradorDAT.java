package NIGGATESTING;
import java.io.*;
import java.util.Random;

public class GeneradorDAT {
    private static final String[] archivos = {
        "mov_tierra.dat", "manual_pala.dat", "martillo.dat", "cibercompresor.dat"
    };
    
    private static final Random random = new Random();
    
    public static void main(String[] args) {
        generarArchivosDAT();
    }
    
    private static void generarArchivosDAT() {
        for (String archivo : archivos) {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo))) {
                for (int j = 0; j < 10; j++) {  // Se generan 10 objetos por archivo
                    escribirDatos(dos, archivo);
                }
                System.out.println("Archivo generado: " + archivo);
            } catch (IOException e) {
                System.out.println("Error al escribir en " + archivo);
            }
        }
    }
    
    private static void escribirDatos(DataOutputStream dos, String archivo) throws IOException {
        switch (archivo) {
            case "mov_tierra.dat":
                dos.writeUTF("Ciberexcavadora");
                dos.writeInt(random.nextInt(5) + 1);
                dos.writeUTF(random.nextBoolean() ? "ruedas" : "oruga");
                dos.writeDouble(random.nextDouble());
                break;
            case "manual_pala.dat":
                dos.writeUTF("pala");
                dos.writeInt(random.nextInt(5) + 1);
                dos.writeUTF(random.nextBoolean() ? "puro" : "aleacion");
                dos.writeUTF(random.nextBoolean() ? "PVC" : "vinilo");
                break;
            case "martillo.dat":
                dos.writeUTF("martillo");
                dos.writeInt(random.nextInt(101) + 200);
                dos.writeUTF(random.nextBoolean() ? "manual" : "correa");
                dos.writeDouble(random.nextDouble());
                break;
            case "cibercompresor.dat":
                dos.writeUTF("cibercompresor");
                dos.writeInt(random.nextInt(5) + 1);
                dos.writeUTF(random.nextBoolean() ? "ruedas" : "oruga");
                dos.writeDouble(random.nextDouble());
                break;
        }
    }
}
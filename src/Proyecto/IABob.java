import java.io.*;
import java.util.Scanner;
public class IABob {
    private static final int filas = 4;
    private static final int columnas = 10;
    private static String[][] maquinaria = new String[filas][columnas];
    private static String[] archivos = {"src//Proyecto//Utils//mov_tierra.dat", "src//Proyecto//Utils//manual_pala.dat", "src//Proyecto//Utils//martillo.dat", "src//Proyecto//Utils//cibercompresor.dat"};


    private static void cargarDatos() {
        for (int i = 0; i < filas; i++) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivos[i]))) {
                for (int j = 0; j < columnas; j++) {
                    maquinaria[i][j] = br.readLine();
                }
            } catch (IOException e) {
                System.out.println("Error al cargar " + archivos[i]);
            }
        }
    }

    private static void mostrarmaquinaria() {
        System.out.println("\nDatos en maquinaria:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.println("Objeto en (" + i + ", " + j + "): " + maquinaria[i][j]);
            }
        }
    }

    private static void modificarObjeto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n¿Desea modificar algún objeto? (si/no)");
        if (!sc.next().equalsIgnoreCase("si")) return;

        System.out.println("Ingrese fila (0-3): ");
        int fila = sc.nextInt();
        System.out.println("Ingrese columna (0-9): ");
        int columna = sc.nextInt();
        if (fila < 0 || fila >= filas || columna < 1 || columna > 3) {
            System.out.println("Modificación no permitida o fuera de rango");
            return;
        }
        
        System.out.println("Ingrese nuevo valor: ");
        maquinaria[fila][columna] = sc.next();
    }

    private static void guardarDatos() {
        for (int i = 0; i < filas; i++) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivos[i]))) {
                for (int j = 0; j < columnas; j++) {
                    bw.write(maquinaria[i][j] + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error al guardar " + archivos[i]);
            }
        }
    }
}

package SQL;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private static String[][] maquinaria = new String[filas][columnas];

    public static void cargarMatriz() {
        for (int i = 0; i < filas; i++) {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(fichas[i]))) {
                for (int j = 0; j < columnas; j++) {
                    String nombre = dis.readUTF(); // Leer el nombre del objeto
                    int valor1 = dis.readInt(); // Leer el primer valor entero
                    String tipo = dis.readUTF(); // Leer el tipo (ruedas/oruga)
                    double valor2 = dis.readDouble(); // Leer el segundo valor numérico (double)
                    maquinaria[i][j] = nombre + " " + valor1 + " " + tipo + " " + valor2;
                }
            } catch (IOException e) {
                System.out.println("Error al cargar " + fichas[i]);
            }
        }

    }

    public static void main(String[] args) {
        cargarMatriz();
        mostrarMaquinaria();
        actualizacionSQL();
        mostrarMaquinaria();

    }

    public static void mostrarMaquinaria() {

        System.out.println("\nDatos en maquinaria:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (maquinaria[i][j] == null) {
                    continue;
                }
                String[] datos = maquinaria[i][j].split(" ");
                if (datos.length < 4) {
                    System.out.println("[Datos incompletos]");
                    continue;
                }
                switch (i) {
                    case 0: // mov_tierra.dat
                        System.out.printf("Ciberexcavadora: %s, Consumo: %s L, Tracción: %s, Protección: %s\n",
                                datos[0], datos[1], datos[2], datos[3]);
                        break;
                    case 1: // manual_pala.dat
                        System.out.printf("Pala: %s, Longitud del Mango: %s m, Metal: %s, Protección: %s\n",
                                datos[0], datos[1], datos[2], datos[3]);
                        break;
                    case 2: // martillo.dat
                        System.out.printf("Martillo: %s, Consumo: %s KW, Sujeción: %s, Protección: %s\n",
                                datos[0], datos[1], datos[2], datos[3]);
                        break;
                    case 3: // cibercompresor.dat
                        System.out.printf("Cibercompresor: %s, Consumo: %s L, Tracción: %s, Protección: %s\n",
                                datos[0], datos[1], datos[2], datos[3]);
                        break;
                }
            }
        }
    }

    public static void actualizacionSQL() {

        System.out.println("1.Actualizacion nocturna\n2.Modificar objeto");

        String opcion = sc.nextLine();

        switch (opcion) {
            case "1":

                break;
            case "2":
                try {
                    System.out.println("Introduce un numero de ficha: ");
                    int numFicha = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introduce el nombre de un elemento (pala1,cibercompresor1..): ");
                    String nombreElemento = sc.nextLine();
                    System.out.println("Introduce el consumo a modificar: ");
                    int consumoElemento = sc.nextInt();


                    for (int i = 0; i < filas; i++) {
                        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichas[i]))) {
                        for (int j = 0; j < columnas; j++) {
                        String[] partes = maquinaria[i][j].split(" ");
                        if(partes[0].equalsIgnoreCase(nombreElemento)){
                        dos.writeInt(consumoElemento);  // Escribir el valor1

                    }
                cargarMatriz();
                    
            }
        } catch (IOException e) {
            System.out.println("Error al guardar " + fichas[i]);
        }
                    
     }} catch (Exception e) {
                    System.out.println("ERROR --> " + e.getMessage());

                }

                break;
            default:
                System.out.println("Introduzca una opcion valida.");
                break;
        }
}

}
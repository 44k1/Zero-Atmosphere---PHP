import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class IABob {
    private static final int filas = 4;
    private static final int columnas = 10;
    private static String[][] maquinaria = new String[filas][columnas];
    private static String[] archivos = {
        "C:\\ProyectoZero\\Zero-Atmosphere---PHP\\src\\Proyecto\\Utils\\mov_tierra.dat",
        "C:\\ProyectoZero\\Zero-Atmosphere---PHP\\src\\Proyecto\\Utils\\manual_pala.dat",
        "C:\\ProyectoZero\\Zero-Atmosphere---PHP\\src\\Proyecto\\Utils\\martillo.dat",
        "C:\\ProyectoZero\\Zero-Atmosphere---PHP\\src\\Proyecto\\Utils\\cibercompresor.dat"
    };

    public static void cargarDatos() {
        for (int i = 0; i < filas; i++) {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(archivos[i]))) {
                for (int j = 0; j < columnas; j++) {
                    if (archivos[i].contains("manual_pala.dat")) {
                        String nombre = dis.readUTF();   // Leer el nombre de la pala
                        int lonMango = dis.readInt();    // Leer la longitud del mango
                        String metal = dis.readUTF();    // Leer el tipo de metal
                        String proteccion = dis.readUTF(); // Leer el tipo de protección
                        maquinaria[i][j] = nombre + " " + lonMango + " " + metal + " " + proteccion;
                    } else {
                        String nombre = dis.readUTF();    // Leer el nombre del objeto
                        int valor1 = dis.readInt();       // Leer el primer valor entero
                        String tipo = dis.readUTF();      // Leer el tipo (ruedas/oruga)
                        double valor2 = dis.readDouble(); // Leer el segundo valor numérico (double)
                        maquinaria[i][j] = nombre + " " + valor1 + " " + tipo + " " + valor2;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al cargar " + archivos[i]);
            }
        }
    }

    public static void mostrarMaquinaria() {
        cargarDatos();
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
    

    public static void modificarObjeto() {
        
    }
    public static ArrayList<Entidad> crearListaSoldadosMineros(String opcion, ArrayList<Entidad> ListaSoldadosMineros, int numEsperadoAliens){
        Scanner sc = new Scanner(System.in);
        if(opcion.equalsIgnoreCase("AUTO")){
        for (int a = 0; a < numEsperadoAliens; a++) {
                try {
                    // Array de generos
                    String[] generos = { "Masculino", "Femenino", "Otro" };
                    // Cargar nombres, apellidos y rangos desde los archivos
                    List<String> nombres = Files.readAllLines(Paths.get("src\\Proyecto\\Utils\\nombres.txt"));
                    List<String> apellidos = Files.readAllLines(Paths.get("src\\Proyecto\\Utils\\apellidos.txt"));
                    List<String> rangos = Files.readAllLines(Paths.get("src\\Proyecto\\Utils\\rangos.txt"));

                    if (nombres.isEmpty() || apellidos.isEmpty() || rangos.isEmpty()) {
                        System.out.println("Uno de los archivos está vacío.");
                        continue; // Saltar a la siguiente iteración si alguno de los archivos está vacío
                    }

                    Random random = new Random();

                    // Selección automática de nombres, apellidos y rangos para los soldados
                    String nombreSoldado1 = nombres.get(random.nextInt(nombres.size())) + " "
                            + apellidos.get(random.nextInt(apellidos.size()));
                    String nombreSoldado2 = nombres.get(random.nextInt(nombres.size())) + " "
                            + apellidos.get(random.nextInt(apellidos.size()));
                    String rangoSoldado1 = rangos.get(random.nextInt(rangos.size()));
                    String rangoSoldado2 = rangos.get(random.nextInt(rangos.size()));

                    // Selección automática de nombres, edades y géneros para los mineros
                    String nombreMinero1 = nombres.get(random.nextInt(nombres.size())) + " "
                            + apellidos.get(random.nextInt(apellidos.size()));
                    String nombreMinero2 = nombres.get(random.nextInt(nombres.size())) + " "
                            + apellidos.get(random.nextInt(apellidos.size()));
                    int edadMinero1 = 18 + random.nextInt(43); // Edad entre 18 y 60 años
                    int edadMinero2 = 18 + random.nextInt(43);
                    String generoMinero1 = generos[random.nextInt(generos.length)];
                    String generoMinero2 = generos[random.nextInt(generos.length)];

                    // Creación de objetos a partir de los datos generados automáticamente
                    Soldado soldado1 = new Soldado(nombreSoldado1, rangoSoldado1);
                    Soldado soldado2 = new Soldado(nombreSoldado2, rangoSoldado2);
                    Minero minero1 = new Minero(nombreMinero1, edadMinero1, generoMinero1);
                    Minero minero2 = new Minero(nombreMinero2, edadMinero2, generoMinero2);

                    // Se añaden dentro del ArrayList los objetos creados
                    ListaSoldadosMineros.add(soldado1);
                    ListaSoldadosMineros.add(soldado2);
                    ListaSoldadosMineros.add(minero1);
                    ListaSoldadosMineros.add(minero2);

                } catch (IOException e) {
                    System.out.println("Error al leer la base de datos de usuarios.");
                }
            }
        } else {

            for (int a = 0; a < numEsperadoAliens; a++) {
                // Solicitud de atributos de los 2 soldados
                System.out.println("Nombre del primer soldado asignado al alien " + (a + 1) + ": ");
                String nombreSoldado1 = sc.nextLine();
                System.out.println("Rango del soldado asignado al alien " + (a + 1) + ": ");
                String rangoSoldado1 = sc.nextLine();
                System.out.println("Nombre del segundo soldado asignado al alien " + (a + 1) + ": ");
                String nombreSoldado2 = sc.nextLine();
                System.out.println("Rango del segundo asignado al alien " + (a + 1) + ": ");
                String rangoSoldado2 = sc.nextLine();
                // Solicitud de atributos de los 2 mineros
                System.out.println("Nombre del primer minero asignado al alien " + (a + 1) + ": ");
                String nombreMinero1 = sc.nextLine();
                System.out.println("Edad del primer minero asignado al alien " + (a + 1) + ": ");
                int edadMinero1 = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                System.out.println("Género del primer minero asignado al alien " + (a + 1) + ": ");
                String generoMinero1 = sc.nextLine();
                System.out.println("Nombre del segundo minero asignado al alien " + (a + 1) + ": ");
                String nombreMinero2 = sc.nextLine();
                System.out.println("Edad del segundo minero asignado al alien " + (a + 1) + ": ");
                int edadMinero2 = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                System.out.println("Género del segundo minero asignado al alien " + (a + 1) + ": ");
                String generoMinero2 = sc.nextLine();
                // Creación de objetos a partir de los datos obtenidos
                Soldado soldado1 = new Soldado(nombreSoldado1, rangoSoldado1);
                Soldado soldado2 = new Soldado(nombreSoldado2, rangoSoldado2);
                Minero minero1 = new Minero(nombreMinero1, edadMinero1, generoMinero1);
                Minero minero2 = new Minero(nombreMinero2, edadMinero2, generoMinero2);
                // Se añaden dentro del ArrayList los objetos creados
                ListaSoldadosMineros.add(soldado1);
                ListaSoldadosMineros.add(soldado2);
                ListaSoldadosMineros.add(minero1);
                ListaSoldadosMineros.add(minero2);
            }

        }
        return ListaSoldadosMineros;
}
    public static void guardarDatos() {
        for (int i = 0; i < filas; i++) {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivos[i]))) {
                for (int j = 0; j < columnas; j++) {
                    String[] partes = maquinaria[i][j].split(" ");
                    // Escribir los datos en el archivo respetando la estructura
                    dos.writeUTF(partes[0]);            // Escribir el nombre
                    dos.writeInt(Integer.parseInt(partes[1]));  // Escribir el valor1
                    dos.writeUTF(partes[2]);            // Escribir el tipo (ruedas/oruga)
                    dos.writeDouble(Double.parseDouble(partes[3])); // Escribir el valor2
                }
            } catch (IOException e) {
                System.out.println("Error al guardar " + archivos[i]);
            }
        }
    }
}

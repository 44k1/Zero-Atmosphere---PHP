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
    private static Maquinaria[][] maquinaria = new Maquinaria[filas][columnas];
    private static String[] archivos = {
        "C:\\ProyectoZero\\Zero-Atmosphere---PHP\\src\\Proyecto\\Utils\\mov_tierra.dat",
        "C:\\ProyectoZero\\Zero-Atmosphere---PHP\\src\\Proyecto\\Utils\\manual_pala.dat",
        "C:\\ProyectoZero\\Zero-Atmosphere---PHP\\src\\Proyecto\\Utils\\martillo.dat",
        "C:\\ProyectoZero\\Zero-Atmosphere---PHP\\src\\Proyecto\\Utils\\cibercompresor.dat"
    };

    public static void cargarDatos() {
            for (int i = 0; i < filas; i++) {
                File f = new File(archivos[i]);
    
                if (!f.exists()) {
                    System.err.println("Error: No se encontró el archivo " + archivos[i]);
                    continue; // Salta a la siguiente iteración si el archivo no existe
                }
    
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                    for (int j = 0; j < columnas; j++) {
                        maquinaria[i][j] = (Maquinaria) ois.readObject();
    
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error al leer el archivo " + archivos[i] + ": " + e.getMessage());
                }
            }
        }
    

    public static String mostrarMaquinaria() {
        cargarDatos();
        String listaMaquinaria = "";
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (maquinaria[i][j] == null) {
                    continue;
                }
                Maquinaria datos = maquinaria[i][j]; // Extraemos el objeto Maquinaria
    
                switch (i) {
                    case 0: // mov_tierra.dat
                    listaMaquinaria+=datos.toString()+"\n";
                        break;
                    case 1: // martillo.dat
                    listaMaquinaria+=datos.toString()+"\n";
                    System.out.println("");
                        break;
                    case 2: //manual_pala.dat
                    listaMaquinaria+=datos.toString()+"\n";  
                    break;
                    case 3: // cibercompresor.dat
                    listaMaquinaria+=datos.toString()+"\n";  
                        break;
                }
                
            }
            
        }
        return listaMaquinaria;
    }
    
    public static void main(String[] args) {
        System.out.println(mostrarMaquinaria());;
    }
    public static void modificarObjeto() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n--- MODIFICAR MAQUINARIA ---");
        System.out.println("1. Pala");
        System.out.println("2. Ciberexcavadora");
        System.out.println("3. Martillo");
        System.out.println("4. Cibercompresor");
        System.out.print("Seleccione el tipo de maquinaria a modificar: ");
        String tipoMaquinaria = sc.nextLine();
    
        try {
            File file = null;
            ArrayList<Maquinaria> maquinas = new ArrayList<>();
            
            // Determinar archivo según selección
            switch(tipoMaquinaria) {
                case "1":
                    file = new File("src/Proyecto/Utils/manual_pala.dat");
                    break;
                case "2":
                    file = new File("src/Proyecto/Utils/mov_tierra.dat");
                    break;
                case "3":
                    file = new File("src/Proyecto/Utils/martillo.dat");
                    break;
                case "4":
                    file = new File("src/Proyecto/Utils/cibercompresor.dat");
                    break;
                default:
                    System.out.println("Opción no válida");
                    return;
            }
    
            // Leer objetos del archivo
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while(true) {
                    try {
                        Maquinaria m = (Maquinaria) ois.readObject();
                        maquinas.add(m);
                    } catch (EOFException e) {
                        break; // Fin del archivo
                    }
                }
            }
    
            // Mostrar lista de máquinas disponibles
            System.out.println("\nLista de máquinas disponibles:");
            for(int i = 0; i < maquinas.size(); i++) {
                System.out.println((i+1) + ". " + maquinas.get(i).toString());
            }
    
            // Seleccionar máquina a modificar
            System.out.print("\nSeleccione el número de máquina a modificar: ");
            int numMaquina = Integer.parseInt(sc.nextLine()) - 1;
    
            if(numMaquina < 0 || numMaquina >= maquinas.size()) {
                System.out.println("Número de máquina no válido");
                return;
            }
    
            Maquinaria maquinaSeleccionada = maquinas.get(numMaquina);
    
            // Mostrar solo atributos modificables
            System.out.println("\nAtributos modificables:");
            if(maquinaSeleccionada instanceof Pala) {
                System.out.println("2. Longitud del mango (1-5 metros)");
                System.out.println("4. Protección (PVC/vinilo)");
            } else if(maquinaSeleccionada instanceof Ciberexcavadora || 
                     maquinaSeleccionada instanceof Martillo || 
                     maquinaSeleccionada instanceof Cibercompresor) {
                System.out.println("2. Consumo");
                System.out.println("4. Protección (valor entre 0 y 1)");
            }
    
            // Seleccionar atributo a modificar
            System.out.print("\nSeleccione el número de atributo a modificar (2 o 4): ");
            String numAtributo = sc.nextLine();
    
            // Validar que solo se puedan modificar los atributos 2 y 4
            if(!numAtributo.equals("2") && !numAtributo.equals("4")) {
                System.out.println("Error: Solo se pueden modificar los atributos 2 y 4");
                return;
            }
    
            // Ingresar nuevo valor
            System.out.print("Ingrese el nuevo valor: ");
            String nuevoValor = sc.nextLine();
    
            // Modificar el atributo usando el método específico de cada clase
            maquinaSeleccionada.modificarObjeto(numAtributo, nuevoValor);
    
            // Guardar cambios en el archivo
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for(Maquinaria m : maquinas) {
                    oos.writeObject(m);
                }
                System.out.println("¡Modificación realizada con éxito!");
            }
    
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
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
}
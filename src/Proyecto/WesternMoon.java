import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WesternMoon {

    public static void main(String[] args) {
        final String key = String.format("%016d", (long) (Math.random() * Math.pow(10, 16)))
                + String.format("%016d", (long) (Math.random() * Math.pow(10, 16)));
        Scanner sc = new Scanner(System.in);
        try {
            mostrarAnimacion();
        } catch (Exception e) {
        }

        // Creación de los 2 AeroCars que hay de reserva y de su responsable
        ResponsableVehiculo responsable1 = new ResponsableVehiculo(null, null, null, null, null, null);
        AeroCars aeroCar1 = new AeroCars("Aerocar de reserva 1", 4, "Gasolina", 200, 10000, responsable1, 4, 200, true);
        AeroCars aeroCar2 = new AeroCars("Aerocar de reserva 2", 4, "Gasolina", 200, 1000, responsable1, 4, 200, true);
        ArrayList<AeroCars> listaAeroCars = new ArrayList<>();
        listaAeroCars.add(aeroCar1);
        listaAeroCars.add(aeroCar2);
        // TEST USERSYSTEM
        String user, password = null;
        try {
            System.out.println("¿Quiere registrar un nuevo usuario? (true/false)");
            boolean opt = sc.nextBoolean();
            sc.nextLine();
            if (opt == true) {
                System.out.println("Registrando usuario..., introduce nombre del usuario");
                user = sc.nextLine();
                System.out.println("Introduce la contraseña:");
                password = UserSystem.getPassSec(sc, key);
                UserSystem.register(user, password, key);
            }
        } catch (Exception e) {

            System.out.println("Error, debe introducir true o false");
        }

        System.out.println("Intentando iniciar sesión..., introduce nombre de usuario");
        user = sc.nextLine();
        System.out.println("Inicio de sesion para " + user);
        password = UserSystem.getPassSec(sc, key);

        boolean loginSuccess = UserSystem.login(user, DecryptOperation.decryptText(password, key)); // Se manda la
                                                                                                    // contraseña
                                                                                                    // desencriptada
                                                                                                    // pedida
                                                                                                    // previamente
        System.out.println("Inicio de sesión " + (loginSuccess ? "exitoso" : "fallido"));
        //

        // Creación de los objetos tripulantes predefinidos
        TripulantePredefinido Laura = new TripulantePredefinido("Laura", 20, "Femenino");
        TripulantePredefinido Carmen = new TripulantePredefinido("Carmen", 30, "Femenino");
        TripulantePredefinido Federico = new TripulantePredefinido("Federico", 19, "Masculino");
        TripulantePredefinido Lopez = new TripulantePredefinido("López", 33, "Masculino");

        DecryptOperation.decrypt(DecryptOperation.decryptText(password, key),
                "UserFiles\\" + user + "\\" + "costesOperacion.txt");
        // Solicitud de datos al usuario
        int numEsperadoAliens = 0;
        while (true) {
            try {
                System.out.println("Introduzca el número esperado de alienígenas: ");
                numEsperadoAliens = sc.nextInt();
                while (numEsperadoAliens < 0) {
                    System.out.println(
                            "ERROR --> El valor a introducir no puede ser un número negativo, por favor, inténtelo de nuevo");
                    sc.nextLine(); // Limpiar buffer
                    System.out.println("Introduzca el número esperado de alienígenas: ");
                    numEsperadoAliens = sc.nextInt();
                }
                sc.nextLine(); // Limpiar buffer
                break;
            } catch (InputMismatchException e) {
                System.out.println("ERROR --> Se debe de introducir un número entero, por favor, inténtelo de nuevo");
                sc.nextLine(); // Limpiar buffer
            }
        }

        // ArrayList con el doble de soldados y mineros que aliens
        ArrayList<Entidad> ListaSoldadosMineros = new ArrayList<>();

        System.out.println("Se van a crear " + (numEsperadoAliens * 2)
                + " soldados y mineros, escriba AUTO si quiere que se le asignen valores automaticos.");
        String opcion = sc.nextLine();
        if (opcion.equalsIgnoreCase("AUTO")) {

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

        // Solicitud de datos al usuario
        float distanciaAñosLuz;
        while (true) {
            try {
                System.out.println("Introduzca la distancia en años luz de la nave DKW-RR.3: ");
                distanciaAñosLuz = sc.nextFloat();
                while (distanciaAñosLuz < 0) {
                    System.out.println(
                            "ERROR --> El valor a introducir no puede ser un número negativo, por favor, inténtelo de nuevo");
                    sc.nextLine(); // Limpiar buffer
                    System.out.println("Introduzca la distancia en años luz de la nave DKW-RR.3: ");
                    distanciaAñosLuz = sc.nextInt();
                }
                sc.nextLine(); // Limpiar buffer
                break;
            } catch (InputMismatchException e) {
                System.out.println(
                        "ERROR --> Se debe de introducir un número con un máximo de dos decimales, por favor, inténtelo de nuevo");
            }
        }
        int numSoldados = 0;
        while (true) {
            try {
                System.out.println(
                        "Se va realizar un listado de soldados, introduzca la cantidad de soldados (Cantidad actual: "
                                + (numEsperadoAliens * 2) + "): ");
                numSoldados = sc.nextInt();
                while (numSoldados < 0) {
                    System.out.println(
                            "ERROR --> El valor a introducir no puede ser un número negativo, por favor, inténtelo de nuevo");
                    sc.nextLine(); // Limpiar buffer
                    System.out.println(
                            "Se va realizar un listado de soldados, introduzca la cantidad de soldados (Cantidad actual: "
                                    + (numEsperadoAliens * 2) + "): ");
                    numSoldados = sc.nextInt();
                }
                sc.nextLine(); // Limpiar Buffer
                break;
            } catch (InputMismatchException e) {
                System.out.println(
                        "ERROR --> Se debe de introducir un número entero, por favor, inténtelo de nuevo");
            }
        }

        // ArrayList del nombre de los soldados
        for (int x = 0; x < numSoldados; x++) {
            // Solicitud de atributos del soldado
            System.out.println("Nombre del soldado número " + ((x + 1) + (numEsperadoAliens * 2)) + ": ");
            String nombreSoldado = sc.nextLine();
            System.out.println("Rango del soldado número " + ((x + 1) + (numEsperadoAliens * 2)) + ": ");
            String rangoSoldado = sc.nextLine();
            // Creación del soldado a partir de los atributos obtenidos
            Soldado soldado3 = new Soldado(nombreSoldado, rangoSoldado);
            // Se añade el soldado al ArrayList
            ListaSoldadosMineros.add(soldado3);
        }

        // Solicitud de datos al usuario
        int numMineros = 0;
        while (true) {
            try {
                System.out.println(
                        "Se va a realizar un listado de mineros, introduzca la cantidad de mineros (Cantidad actual: "
                                + (numEsperadoAliens * 2) + "): ");
                numMineros = sc.nextInt();
                while (numMineros < 0) {
                    System.out.println(
                            "ERROR --> El valor a introducir no puede ser un número negativo, por favor, inténtelo de nuevo");
                    sc.nextLine(); // Limpiar buffer
                    System.out.println(
                            "Se va a realizar un listado de mineros, introduzca la cantidad de mineros (Cantidad actual: "
                                    + (numEsperadoAliens * 2) + "): ");
                    numSoldados = sc.nextInt();
                }
                sc.nextLine(); // Limpiar Buffer
                break;
            } catch (InputMismatchException e) {
                System.out.println(
                        "ERROR --> Se debe de introducir un número entero, por favor, inténtelo de nuevo");
            }
        }

        // ArrayList del nombre de los mineros
        for (int y = 0; y < numMineros; y++) {
            // Solicitud de atributos del minero
            System.out.println("Nombre del minero número " + ((y + 1) + (numEsperadoAliens * 2)) + ": ");
            String nombreMinero = sc.nextLine();
            int edadMinero = 0;
            while (true) {
                try {
                    System.out.println("Edad del minero número " + ((y + 1) + (numEsperadoAliens * 2)) + ": ");
                    edadMinero = sc.nextInt();
                    while (edadMinero < 0) {
                        System.out.println(
                                "ERROR --> El valor a introducir no puede ser un número negativo, por favor, inténtelo de nuevo");
                        sc.nextLine(); // Limpiar buffer
                    }
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(
                            "ERROR --> Se debe de introducir un número entero, por favor, inténtelo de nuevo");
                }
            }
            System.out.println("Género del minero número " + ((y + 1) + (numEsperadoAliens * 2)) + ": ");
            String generoMinero = sc.nextLine();
            // Creación del minero a partir de los atributos obtenidos
            Minero minero3 = new Minero(nombreMinero, edadMinero, generoMinero);
            // Se añade el soldado al ArrayList
            ListaSoldadosMineros.add(minero3);
        }
        int numAerocarsUtilizar = 0;
        while (true) {
            try {
                System.out.println("Introduzca el número de aerocars a utilizar a demás de los 2 de reserva: ");
                numAerocarsUtilizar = sc.nextInt();
                while (numAerocarsUtilizar < 0) {
                    System.out.println(
                            "ERROR --> El valor a introducir no puede ser un número negativo, por favor, inténtelo de nuevo");
                    sc.nextLine(); // Limpiar buffer
                }
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println(
                        "ERROR --> Se debe de introducir un número entero, por favor, inténtelo de nuevo");
            }
        }
        for (int z = 0; z < numAerocarsUtilizar; z++) {
            AeroCars aerocar3 = new AeroCars();
            listaAeroCars.add(aerocar3);
        }

        // Se calcula el coste de cada aerocar en base a la distancia
        System.out.println("Introduzca la distancia en años luz que van a recorrer los aeroscars");
        double distancia = sc.nextFloat();
        double raizCuadradaDistancia = Math.sqrt(distancia);
        double costeCombustible = raizCuadradaDistancia * 12;
        // Muestra en pantalla la información del array de mineros y soldados
        mostrarAtributos(ListaSoldadosMineros);

        System.out.println();

        // Muestra en pantalla los costes de operación
        costesOperacion(numEsperadoAliens, distanciaAñosLuz, numSoldados, numMineros, numAerocarsUtilizar,
                ListaSoldadosMineros, costeCombustible, listaAeroCars, sc, password, key, user);

    }

    // FUNCIÓN ENCARGADA DE REALIZAR LA OPERACIÓN DE LOS COSTES DE LA OPERACIÓN,
    // MUESTRA EL NÚMERO DE ELEMENTOS QUE HAN PARTICIPADO EN LA OPERACIÓN Y LOS
    // GASTOS QUE HAN PROVOCADO

    public static void costesOperacion(int numEsperadoAliens, float distanciaAñosLuz, int numSoldados, int numMineros,
            int numAerocarsUtilizar, ArrayList<Entidad> arraylist, double costeCombustible,
            ArrayList<AeroCars> listaAeroCars, Scanner sc, String password, String key, String user) {
        System.out.println("¿Quieres guardar los costes de operación y encriptarlos? (true/false)");
        boolean guardarOperacion = sc.nextBoolean();
        float yursSoldados = 0;
        float yursMineros = 0;
        // Cálculo de coste de aerocars
        double yursAerocars = listaAeroCars.size() * costeCombustible;
        for (Entidad objeto : arraylist) {
            if (objeto instanceof Soldado) {
                yursSoldados = yursSoldados + 22;
            } else {
                yursMineros = yursMineros + 20;
            }
        }
        String resultado = "< ---------- COSTES DE LA OPERACIÓN ---------- >\n\nDistancia de la nave DKW-RR.3: "
                + distanciaAñosLuz
                + " años luz\nNúmero de alienígenas encontrados durante la operación: " + numEsperadoAliens + " + "
                + (numEsperadoAliens * 4) + "yurs\nNúmero de soldados en la nave: "
                + (numSoldados + (numEsperadoAliens * 2)) + " + "
                + (yursSoldados * 3) + "yurs\nNúmero de mineros en la nave: " + (numMineros + (numEsperadoAliens * 2))
                + " + "
                + (yursMineros * 3) + "yurs\nNúmero de aerocars utilizados durante la operación: " + numAerocarsUtilizar
                + "\n\nTOTAL: " + (((yursMineros * 3)) + (3 * yursSoldados) + (numEsperadoAliens * 4))
                + (yursMineros * 3) + "yurs\nNúmero de aerocars utilizados durante la operación: "
                + listaAeroCars.size()
                + " + " + yursAerocars
                + "yurs\n\nTOTAL: "
                + (((yursMineros * 3)) + (3 * yursSoldados) + (numEsperadoAliens * 4) + yursAerocars)
                + " YURS < -----";
        System.out.println(resultado);

        if (guardarOperacion) {

            // Encriptar el resultado y guardarlo en un archivo
            String encryptedResult = EncryptOperation.encrypt(resultado, DecryptOperation.decryptText(password, key)); // Cambia
                                                                                                                       // la
                                                                                                                       // clave
                                                                                                                       // según
            // necesidad
            EncryptOperation.saveToFile("UserFiles\\" + user + "\\costesOperacion.txt", encryptedResult);
        }

    }

    // FUNCIÓN ENCARGADA DE RECIBIR UN ARRAYLIST DE TIPO ENTIDAD QUE SE ENCARGA DE
    // MOSTRAR LOS ATRIBUTOS DE CADA OBJETO DENTRO DE EL CON EL MÉTODO TO STRING

    public static void mostrarAtributos(ArrayList<Entidad> arraylist) {

        int contador = 1;
        System.out.println("< ---------- INFORMACIÓN DE MINEROS Y SOLDADOS ---------- >\n");
        for (Entidad objeto : arraylist) {
            System.out.println("Elemento " + contador + ": " + objeto.toString());
            contador++;
        }
        System.out.println();
    }

    // Función que muestra la animación con el texto, retraso y color
    public static void mostrarAnimacion() throws InterruptedException {
        // Definir el ASCII art para "WesternMoon" (puedes personalizar este arte)
        String[] asciiArt = {
            " ___       __   _____ ______      \r\n", //
                                "|\\  \\     |\\  \\|\\   _ \\  _   \\    \r\n",//
                                "\\ \\  \\    \\ \\  \\ \\  \\\\\\__\\ \\  \\   \r\n", //
                                " \\ \\  \\  __\\ \\  \\ \\  \\\\|__| \\  \\  \r\n", //
                                "  \\ \\  \\|\\__\\_\\  \\ \\  \\    \\ \\  \\ \r\n", //
                                "   \\ \\____________\\ \\__\\    \\ \\__\\\r\n", //
                                "    \\|____________|\\|__|     \\|__|\n",
                                "       WesternMoon by PHPTeam!"
        };
        
    
        // Colores temáticos lunares
        String[] colores = {
            "\u001B[37m", // Blanco (Luna llena)
            "\u001B[34m", // Azul (Cielo nocturno)
            "\u001B[36m", // Cian (Reflejo lunar)
            "\u001B[90m", // Gris oscuro (Sombra lunar)
            "\u001B[35m"  // Magenta (Aurora lunar)
        };
    
        // Animación con cambio de color para cada línea de ASCII art
        for (int i = 0; i < asciiArt.length; i++) {
            // Elegir un color del array basado en la posición
            String color = colores[i % colores.length];
    
            // Imprimir la línea con el color seleccionado
            System.out.print(color + asciiArt[i] + "\u001B[0m");
            
            // Esperar un poco antes de imprimir la siguiente línea
            Thread.sleep(100);  
        }
        
    
        // Línea nueva después de la animación
        System.out.println();
    }
}
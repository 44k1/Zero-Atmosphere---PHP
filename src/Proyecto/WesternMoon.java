import java.util.*;

public class WesternMoon {

    public static void main(String[] args) {

        // Creación del escaner
        Scanner sc = new Scanner(System.in);

        // Creación de los objetos tripulantes predefinidos
        TripulantePredefinido Laura = new TripulantePredefinido("Laura", 20, "Femenino");
        TripulantePredefinido Carmen = new TripulantePredefinido("Carmen", 30, "Femenino");
        TripulantePredefinido Federico = new TripulantePredefinido("Federico", 19, "Masculino");
        TripulantePredefinido Lopez = new TripulantePredefinido("López", 33, "Masculino");

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
                System.out.println("Introduzca el número de aerocars a utilizar: ");
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

        // Muestra en pantalla la información del array de mineros y soldados

        mostrarAtributos(ListaSoldadosMineros);

        System.out.println();

        // Muestra en pantalla los costes de operación
        System.out.println(
                costesOperacion(numEsperadoAliens, distanciaAñosLuz, numSoldados, numMineros, numAerocarsUtilizar,
                        ListaSoldadosMineros));

    }

    // FUNCIÓN ENCARGADA DE REALIZAR LA OPERACIÓN DE LOS COSTES DE LA OPERACIÓN,
    // MUESTRA EL NÚMERO DE ELEMENTOS QUE HAN PARTICIPADO EN LA OPERACIÓN Y LOS
    // GASTOS QUE HAN PROVOCADO

    public static String costesOperacion(int numEsperadoAliens, float distanciaAñosLuz, int numSoldados, int numMineros,
            int numAerocarsUtilizar, ArrayList<Entidad> arraylist) {

        float yursSoldados = 0;
        float yursMineros = 0;
        for (Entidad objeto : arraylist) {
            if (objeto instanceof Soldado) {
                yursSoldados = yursSoldados + 22;
            } else {
                yursMineros = yursMineros + 20;
            }
        }

        return "< ---------- COSTES DE LA OPERACIÓN ---------- >\n\nDistancia de la nave DKW-RR.3: " + distanciaAñosLuz
                + " años luz\nNúmero de alienígenas encontrados durante la operación: " + numEsperadoAliens + " + "
                + (numEsperadoAliens * 4) + "yurs\nNúmero de soldados en la nave: "
                + (numSoldados + (numEsperadoAliens * 2)) + " + "
                + (yursSoldados * 3) + "yurs\nNúmero de mineros en la nave: " + (numMineros + (numEsperadoAliens * 2))
                + " + "
                + (yursMineros * 3) + "yurs\nNúmero de aerocars utilizados durante la operación: " + numAerocarsUtilizar
                + "\n\nTOTAL: " + (((yursMineros * 3)) + (3 * yursSoldados) + (numEsperadoAliens * 4))
                + " YURS < -----";
    }

    // FUNCIÓN ENCARGADA DE RECIBIR UN ARRAYLIST DE TIPO ENTIDAD QUE SE ENCARGA DE
    // MOSTRAR LOS ATRIBUTOS DE CADA OBJETO DENTRO DE EL CON EL MÉTODO TO STRING

    public static void mostrarAtributos(ArrayList<Entidad> arraylist) {

        int contador = 1;
        System.out.println("< ---------- INFORMACIÓN DE MINEROS Y SOLDADOS ---------- >\n");
        for (Entidad objeto : arraylist) {
            System.out.println("Elemento " + contador + ": " + objeto);
            contador++;
        }
        System.out.println();
    }

}

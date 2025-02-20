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
                    sc.nextLine();
                    System.out.println("Introduzca el número esperado de alienígenas: ");
                    numEsperadoAliens = sc.nextInt();
                }
                sc.nextLine(); // Limpiar buffer
                break;
            } catch (InputMismatchException e) {
                System.out.println("ERROR --> Se debe de introducir un número entero, por favor, inténtelo de nuevo");
                sc.nextLine();
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

        mostrarAtributos(ListaSoldadosMineros);

        // Solicitud de datos al usuario
        System.out.println("Introduzca la distancia en años luz de la nave DKW-RR.3: ");
        float distanciaAñosLuz = sc.nextFloat();
        System.out.println("Se va realizar un listado de soldados, introduzca la cantidad de soldados: ");
        int numSoldados = sc.nextInt();
        sc.nextLine(); // Limpiar Buffer

        // Array del nombre de los soldados
        String[] listaSoldatos = new String[numSoldados];
        for (int x = 0; x < numSoldados; x++) {
            System.out.println("Introduzca el nombre del soldado número " + (x + 1));
            listaSoldatos[x] = sc.nextLine();
        }

        // Solicitud de datos al usuario
        System.out.println("Se va a realizar un listado de mineros, introduzca la cantidad de mineros: ");
        int numMineros = sc.nextInt();
        sc.nextLine(); // Limpiar Buffer

        // Array del nombre de los mineros

        String[] listaMineros = new String[numMineros];
        for (int y = 0; y < numMineros; y++) {
            System.out.println("Introduzca el nombre del minero número " + (y + 1) + ": ");
            listaMineros[y] = sc.nextLine();
        }
        System.out.println("Introduzca el número de aerocars a utilizar: ");
        int numAerocarsUtilizar = sc.nextInt();

        // Muestra en pantalla la información de los mineros y los soldados
        System.out.println("< ---------- INFORMACIÓN DE SOLDADOS Y MINEROS ---------- >\n");

        for (int x = 0; x < numSoldados; x++) {
            System.out.println("Soldado " + (x + 1) + ": " + listaSoldatos[x]);
        }

        for (int y = 0; y < numMineros; y++) {
            System.out.println("Minero " + (y + 1) + ": " + listaMineros[y]);
        }

        System.out.println();

        // Muestra en pantalla los costes de operación
        System.out.println(
                costesOperacion(numEsperadoAliens, distanciaAñosLuz, numSoldados, numMineros, numAerocarsUtilizar));

    }

    // FUNCIÓN ENCARGADA DE REALIZAR LA OPERACIÓN DE LOS COSTES DE LA OPERACIÓN,
    // MUESTRA EL NÚMERO DE ELEMENTOS QUE HAN PARTICIPADO EN LA OPERACIÓN Y LOS
    // GASTOS QUE HAN PROVOCADO

    public static String costesOperacion(int numEsperadoAliens, float distanciaAñosLuz, int numSoldados, int numMineros,
            int numAerocarsUtilizar) {
        return "< ---------- COSTES DE LA OPERACIÓN ---------- >\n\nDistancia de la nave DKW-RR.3: " + distanciaAñosLuz
                + " años luz\nNúmero de alienígenas encontrados durante la operación: " + numEsperadoAliens + " + "
                + 3 * (numEsperadoAliens * 4) + "\nNúmero de soldados en la nave: " + numSoldados + " + "
                + 3 * (numSoldados * 22) + "\nNúmero de mineros en la nave: " + numMineros + " + "
                + 3 * (numMineros * 20) + "\nNúmero de aerocars utilizados durante la operación: " + numAerocarsUtilizar
                + "\n\nTOTAL: " + ((3 * (numMineros * 20)) + (3 * (numSoldados * 22)) + (3 * (numEsperadoAliens * 4)));
    }

    // FUNCIÓN ENCARGADA DE RECIBIR UN ARRAYLIST DE TIPO ENTIDAD QUE SE ENCARGA DE
    // MOSTRAR LOS ATRIBUTOS DE CADA OBJETO DENTRO DE EL CON EL MÉTODO TO STRING

    public static void mostrarAtributos(ArrayList<Entidad> arraylist) {

        int contador = 1;
        System.out.println("< ---------- INFORMACIÓN DE MINEROS Y SOLDADOS ASIGNADOS POR ALIEN ---------- >\n");
        for (Entidad objeto : arraylist) {
            System.out.println("Elemento " + contador + ": " + objeto.toString());
            contador++;
        }
        System.out.println();
    }

}

import java.util.*;

public class WesternMoon {

    public static void main(String[] args) {

        // Creación del escaner
        Scanner sc = new Scanner(System.in);

        // Solicitud de datos al usuario

        System.out.println("Introduzca el número esperado de alienígenas: ");
        int numEsperadoAliens = sc.nextInt();
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

}

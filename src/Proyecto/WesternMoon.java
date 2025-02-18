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
        String[] listaSoldatos = new String[numSoldados];
        for (int x = 0; x < numSoldados; x++) {
            System.out.println("Introduzca el nombre del soldado número " + (x + 1));
            listaSoldatos[x] = sc.nextLine();
        }
        System.out.println("Se va a realizar un listado de mineros, introduzca la cantidad de mineros: ");
        int numMineros = sc.nextInt();
        String[] listaMineros = new String[numMineros];
        for (int y = 0; y < numMineros; y++) {
            System.out.println("Introduzca el nombre del minero número " + (y + 1) + ": ");
            listaMineros[y] = sc.nextLine();
        }
        System.out.println("Introduzca el número de aerocars a utilizar: ");
        int numAerocarsUtilizar = sc.nextInt();
        

    }

}
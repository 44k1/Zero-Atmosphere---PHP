import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class WesternMoonTest {


    // Variables de configuración
    private static final String PASSWORD = "1234567890123456"; // Clave de 16 caracteres para AES
    private static final String FILE_PATH = "UserFiles/user1/costesOperacion.txt";
    
    // Datos de prueba
    private int numEsperadoAliens = 10;
    private float distanciaAñosLuz = 5.0f;
    private int numSoldados = 20;
    private int numMineros = 15;
    private int numAerocarsUtilizar = 5;
    private double costeCombustible = 50.0;
    private List<AeroCars> listaAeroCars;
    private List<Entidad> arraylist;

    @BeforeEach
    void setUp() {
        // Inicialización de datos antes de cada prueba
        listaAeroCars = new ArrayList<>();
        arraylist = new ArrayList<>();
        
        // Agregar elementos a las listas
        listaAeroCars.add(new AeroCars("AeroCar 1", numAerocarsUtilizar, null, numAerocarsUtilizar, numAerocarsUtilizar, null, numAerocarsUtilizar, numAerocarsUtilizar, false));
        listaAeroCars.add(new AeroCars("AeroCar 2", numAerocarsUtilizar, null, numAerocarsUtilizar, numAerocarsUtilizar, null, numAerocarsUtilizar, numAerocarsUtilizar, false));
        
        arraylist.add(new Soldado("Soldado 1", null));
        arraylist.add(new Soldado("Soldado 2", null));
        arraylist.add(new Minero("Minero 1", numAerocarsUtilizar, null));
        arraylist.add(new Minero("Minero 2", numAerocarsUtilizar, null));
    }

    // ---------------------------- Pruebas de Encriptación y Desencriptación ----------------------------

    @Test
    void testCostesOperacionEncriptados() {
        // Datos esperados
        String expectedOutput = "< ---------- COSTES DE LA OPERACIÓN ---------- >\n\n" +
                "Distancia de la nave DKW-RR.3: 5.0 años luz\n" +
                "Número de alienígenas encontrados durante la operación: 10 + 40yurs\n" +
                "Número de soldados en la nave: 30 + 1320.0yurs\n" +
                "Número de mineros en la nave: 25 + 1200.0yurs\n" +
                "Número de aerocars utilizados durante la operación: 5\n\n" +
                "TOTAL: 1560.0yurs\n" +
                "Número de aerocars utilizados durante la operación: 2 + 100.0yurs\n\n" +
                "TOTAL: 1660.0 YURS < -----";
        
        // Encriptar el resultado
        String encryptedResult = EncryptOperation.encrypt(expectedOutput, PASSWORD);

        // Verificaciones
        assertNotNull(encryptedResult);
        assertNotEquals(expectedOutput, encryptedResult);
        
        // Guardar el archivo con el resultado encriptado
        EncryptOperation.saveToFile(FILE_PATH, encryptedResult);
        assertTrue(Files.exists(Paths.get(FILE_PATH)), "El archivo debe existir después de guardar el resultado.");
    }

    @Test
    void testCostesOperacionDesencriptados() {
        // Encriptar y guardar
        String expectedOutput = "< ---------- COSTES DE LA OPERACIÓN ---------- >\n\n" +
                "Distancia de la nave DKW-RR.3: 5.0 años luz\n" +
                "Número de alienígenas encontrados durante la operación: 10 + 40yurs\n" +
                "Número de soldados en la nave: 30 + 1320.0yurs\n" +
                "Número de mineros en la nave: 25 + 1200.0yurs\n" +
                "Número de aerocars utilizados durante la operación: 5\n\n" +
                "TOTAL: 1560.0yurs\n" +
                "Número de aerocars utilizados durante la operación: 2 + 100.0yurs\n\n" +
                "TOTAL: 1660.0 YURS < -----";
        
        String encryptedResult = EncryptOperation.encrypt(expectedOutput, PASSWORD);
        EncryptOperation.saveToFile(FILE_PATH, encryptedResult);
        
        // Desencriptar el archivo
        String decryptedText = DecryptOperation.decryptParaGUI(PASSWORD, FILE_PATH);

        // Verificar que el texto desencriptado es el esperado
        assertNotNull(decryptedText);
        assertEquals(expectedOutput, decryptedText, "El texto desencriptado debe ser igual al texto original");
    }

    @Test
    void testEncryptDecryptFlow() {
        // Test para verificar el flujo completo de encriptación y desencriptación
        String testText = "Este es un mensaje para pruebas de encriptado y desencriptado.";
        
        // Encriptar
        String encryptedText = EncryptOperation.encrypt(testText, PASSWORD);
        assertNotEquals(testText, encryptedText, "El texto encriptado no debe ser igual al original");

        // Desencriptar
        String decryptedText = DecryptOperation.decryptText(encryptedText, PASSWORD);
        assertEquals(testText, decryptedText, "El texto desencriptado debe ser igual al original");
    }

    // ---------------------------- Pruebas de Funcionalidad ----------------------------

    @Test
    void testCostesOperacion() {
        // Datos de prueba
        int numEsperadoAliens = 5;
        float distanciaAñosLuz = 10.5f;
        int numSoldados = 3;
        int numMineros = 2;
        int numAerocarsUtilizar = 1;
        double costeCombustible = 15.0;
        
        ArrayList<Entidad> arraylist = new ArrayList<>();
        arraylist.add(new Soldado(null, null));
        arraylist.add(new Soldado(null, null));
        arraylist.add(new Minero());
        
        ArrayList<AeroCars> listaAeroCars = new ArrayList<>();
        listaAeroCars.add(new AeroCars());
        
        // Simular que el usuario elige "false" para no guardar ni encriptar
        String simulatedInput = "false\n"; 
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner sc = new Scanner(inputStream);
        
        // Llamada al método de costesOperacion
        String password = "password123";
        String key = "key123";
        String user = "usuarioPrueba1";
        
        // Llamar al método que genera los costes
        WesternMoon.costesOperacion(numEsperadoAliens, distanciaAñosLuz, numSoldados, numMineros,
                numAerocarsUtilizar, arraylist, costeCombustible, listaAeroCars, sc, password, key, user);
    }


    // ---------------------------- Pruebas de Entrada y Salida ----------------------------

    @Test
    void testEntradaUsuarioGuardarOperacion() {
        // Simulación de entrada: el usuario elige "true" para guardar
        String simulatedInput = "true\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner sc = new Scanner(inputStream);
        
        boolean guardarOperacion = sc.nextBoolean();
        assertTrue(guardarOperacion);
    }

    @Test
    void testEntradaUsuarioNoGuardarOperacion() {
        // Simulación de entrada: el usuario elige "false" para no guardar
        String simulatedInput = "false\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner sc = new Scanner(inputStream);
        
        boolean guardarOperacion = sc.nextBoolean();
        assertFalse(guardarOperacion);
    }

    // ---------------------------- Pruebas de Animación ----------------------------

    @Test
    void testMostrarAnimacion() {
        try {
            // Llamar al método de animación
            WesternMoon.mostrarAnimacion();
        } catch (InterruptedException e) {
            fail("La animación fue interrumpida.");
        }
    }

    @Test
    void testFuncionamientoAnimacion() {
        // Captura de salida estándar para verificar la animación
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        try {
            WesternMoon.mostrarAnimacion();
            String output = outputStream.toString();
            assertTrue(output.contains("WesternMoon"));
            assertTrue(output.contains("by PHPTeam"));
        } catch (InterruptedException e) {
            fail("La animación fue interrumpida.");
        } finally {
            System.setOut(System.out);
        }
    }


	//---------------------------- Pruebas de Modificacion Maquinarias ----------------------------
    //Tuve que quitar final a la matriz de maquinaria para poder hacer testing.
    @Test
    void testCargarDatos() {
    	IABob.cargarDatos();  // Carga los datos
    	// Verifica si maquinaria fue cargada correctamente (se puede hacer comprobando el estado de la matriz maquinaria).
    	assertNotNull(IABob.maquinaria);
    	// Verifica que al menos un objeto maquinaria se haya cargado.
    	assertNotNull(IABob.maquinaria[0][0]); 
    }

    @Test
    void testModificarObjeto() {
    	// Primero, cargamos los datos de la maquinaria
    	IABob.cargarDatos();
    
    	// Simulamos la entrada del usuario para modificar una maquinaria (por ejemplo, "2" para seleccionar la maquinaria 2)
    	ByteArrayInputStream inputStream = new ByteArrayInputStream("2\n2\n2\n4\n".getBytes());
    	System.setIn(inputStream);  // Redirigimos la entrada estándar

    	IABob.modificarObjeto();  // Ejecutamos la modificación
    }
}

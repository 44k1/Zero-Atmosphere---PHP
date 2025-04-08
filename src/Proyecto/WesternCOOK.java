import java.util.Scanner;

public class WesternCOOK {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("MENU PRINCIPAL");
        System.out.println("1. Ejecutar CLI (WesternMoon)");
        System.out.println("2. Ejecutar GUI (WesternMoonFX)");
        System.out.print("Seleccione una opción (1-2): ");
        
        int opcion = sc.nextInt();
        
        try {
            ProcessBuilder pb = new ProcessBuilder();
            
            switch(opcion) {
                case 1:
                    System.out.println("Ejecutando WesternMoon (CLI)...");
                    pb.command("powershell.exe", "-Command", 
                              "cd 'c:\\ProyectoZero\\Zero-Atmosphere---PHP'; " +
                              "& 'C:\\Program Files\\Java\\jdk-21\\bin\\java.exe' " +
                              "'@C:\\Users\\migue\\AppData\\Local\\Temp\\cp_1qz1glabm5zj4ok3s23j8snh7.argfile' " +
                              "'WesternMoon'");
                    break;
                case 2:
                    System.out.println("Ejecutando WesternMoonFX (GUI)...");
                    pb.command("powershell.exe", "-Command", 
                              "cd 'c:\\ProyectoZero\\Zero-Atmosphere---PHP'; " +
                              "& 'C:\\Program Files\\Java\\jdk-21\\bin\\java.exe' " +
                              "--module-path \"c:\\!DAM\\Programacion\\JavaFX\\javafx-sdk-21.0.6\\lib\" " +
                              "--add-modules javafx.controls,javafx.fxml " +
                              "'@C:\\Users\\migue\\AppData\\Local\\Temp\\cp_1qz1glabm5zj4ok3s23j8snh7.argfile' " +
                              "'WesternMoonFX'");
                    break;
                default:
                    System.out.println("Opción no válida");
                    return;
            }
            
            pb.inheritIO(); 
            Process process = pb.start();
            process.waitFor();
            
        } catch (Exception e) {
            System.err.println("Error al ejecutar el comando: " + e.getMessage());
            e.printStackTrace();
        } 
    }
}
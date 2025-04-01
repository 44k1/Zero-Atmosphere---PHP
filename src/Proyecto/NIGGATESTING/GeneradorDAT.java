import java.io.*;
import java.util.Random;
import java.util.Scanner;



public class GeneradorDAT {
    private static final String[] archivos = {
            "src\\Proyecto\\Utils\\mov_tierra.dat", "src\\Proyecto\\Utils\\manual_pala.dat", "src\\Proyecto\\Utils\\martillo.dat", "src\\Proyecto\\Utils\\cibercompresor.dat"
    };

    private static final long serialVersionUID = 1L;


    public static void main(String[] args) {
        try {
            escribirDatos();
            leerDatos();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
        ;

    }

    private static void escribirDatos() throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            String archivo = sc.nextLine();
            File f = new File(archivo);
            FileOutputStream fos = new FileOutputStream(f);// Se le paso
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            switch (archivo) {
                case "Ciberexcavadora":
                    f = new File("src\\Proyecto\\Utils\\mov_tierra.dat");
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(new Ciberexcavadora("Ciberexcavadora1", 1, "ruedas", 0.8));
                    oos.writeObject(new Ciberexcavadora("Ciberexcavadora2", 3, "oruga", 0.5));
                    oos.writeObject(new Ciberexcavadora("Ciberexcavadora3", 2, "ruedas", 0.9));
                    oos.writeObject(new Ciberexcavadora("Ciberexcavadora4", 5, "oruga", 0.3));
                    oos.writeObject(new Ciberexcavadora("Ciberexcavadora5", 4, "ruedas", 0.6));
                    oos.writeObject(new Ciberexcavadora("Ciberexcavadora6", 1, "oruga", 0.2));
                    oos.writeObject(new Ciberexcavadora("Ciberexcavadora7", 2, "ruedas", 0.7));
                    oos.writeObject(new Ciberexcavadora("Ciberexcavadora8", 3, "oruga", 0.4));
                    oos.writeObject(new Ciberexcavadora("Ciberexcavadora9", 5, "ruedas", 0.1));
                    oos.writeObject(new Ciberexcavadora("Ciberexcavadora10", 4, "oruga", 0.95));
                    oos.close();
                    break;
                case "Pala":
                    f = new File("src\\Proyecto\\Utils\\manual_pala.dat");
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(new Pala("Pala1", 1, "puro", "PVC"));
                    oos.writeObject(new Pala("Pala2", 3, "aleacion", "vinilo"));
                    oos.writeObject(new Pala("Pala3", 2, "puro", "PVC"));
                    oos.writeObject(new Pala("Pala4", 5, "aleacion", "vinilo"));
                    oos.writeObject(new Pala("Pala5", 4, "puro", "PVC"));
                    oos.writeObject(new Pala("Pala6", 1, "aleacion", "vinilo"));
                    oos.writeObject(new Pala("Pala7", 2, "puro", "PVC"));
                    oos.writeObject(new Pala("Pala8", 3, "aleacion", "vinilo"));
                    oos.writeObject(new Pala("Pala9", 5, "puro", "PVC"));
                    oos.writeObject(new Pala("Pala10", 4, "aleacion", "vinilo"));
                    oos.close();

                    break;
                case "Martillo":
                    f = new File("src\\Proyecto\\Utils\\martillo.dat");
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(new Martillo("Martillo1", 200, "manual", 0.8));
                    oos.writeObject(new Martillo("Martillo2", 250, "correa", 0.5));
                    oos.writeObject(new Martillo("Martillo3", 220, "manual", 0.9));
                    oos.writeObject(new Martillo("Martillo4", 300, "correa", 0.3));
                    oos.writeObject(new Martillo("Martillo5", 280, "manual", 0.6));
                    oos.writeObject(new Martillo("Martillo6", 210, "correa", 0.2));
                    oos.writeObject(new Martillo("Martillo7", 230, "manual", 0.7));
                    oos.writeObject(new Martillo("Martillo8", 270, "correa", 0.4));
                    oos.writeObject(new Martillo("Martillo9", 290, "manual", 0.1));
                    oos.writeObject(new Martillo("Martillo10", 260, "correa", 0.95));
                    oos.close();

                    break;
                case "Cibercompresor":
                    f = new File("src\\Proyecto\\Utils\\cibercompresor.dat");
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(new Cibercompresor("Cibercompresor1", 1, "ruedas", 0.8));
                    oos.writeObject(new Cibercompresor("Cibercompresor2", 2, "oruga", 0.5));
                    oos.writeObject(new Cibercompresor("Cibercompresor3", 3, "ruedas", 0.9));
                    oos.writeObject(new Cibercompresor("Cibercompresor4", 4, "oruga", 0.3));
                    oos.writeObject(new Cibercompresor("Cibercompresor5", 5, "ruedas", 0.6));
                    oos.writeObject(new Cibercompresor("Cibercompresor6", 1, "oruga", 0.2));
                    oos.writeObject(new Cibercompresor("Cibercompresor7", 2, "ruedas", 0.7));
                    oos.writeObject(new Cibercompresor("Cibercompresor8", 3, "oruga", 0.4));
                    oos.writeObject(new Cibercompresor("Cibercompresor9", 4, "ruedas", 0.1));
                    oos.writeObject(new Cibercompresor("Cibercompresor10", 5, "oruga", 0.95));

                    oos.close();
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static void leerDatos() throws IOException, ClassNotFoundException {
    for (int i = 0; i < archivos.length; i++) { // Corrección de la condición
        File f = new File(archivos[i]);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            System.out.println("Leyendo archivo: " + archivos[i]);

            while (true) {
                try {
                    Object obj = ois.readObject(); // Leer objeto genérico

                    if (obj instanceof Cibercompresor) {
                        System.out.println("Cibercompresor: " + obj.toString());
                    } else if (obj instanceof Pala) {
                        System.out.println("Pala: " + obj.toString());
                    } else if (obj instanceof Ciberexcavadora) {
                        System.out.println("Ciberexcavadora: " + obj.toString());
                    } else if (obj instanceof Martillo) {
                        System.out.println("Martillo: " + obj.toString());
                    } else {
                        System.out.println("Objeto desconocido en " + archivos[i]);
                    }

                    System.out.println("*****");
                } catch (EOFException e) {
                    break; // Fin del archivo
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura en " + archivos[i] + ": " + e.getMessage());
        }
    }
}
} 

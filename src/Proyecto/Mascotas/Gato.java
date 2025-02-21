public class Gato extends Mascota{
    static int contador=0;
    public Gato(String nombre) {
        super(nombre);
    }
    
    @Override
    public void comunicar() {
        System.out.println(getNombre() + " maúlla: ¡Miau!");
    }
}

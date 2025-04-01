public class Gato extends Mascota{
    public Gato(String nombre) {
        super(nombre);
    }
    
    @Override
    public void comunicar() {
        System.out.println(getNombre() + " maúlla: ¡Miau!");
    }
}

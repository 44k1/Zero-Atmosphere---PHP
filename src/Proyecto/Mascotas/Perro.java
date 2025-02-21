public class Perro extends Mascota{
    static int contador=0;
    
    public Perro(String nombre) {
        super(nombre);
    }
    
    @Override
    public void comunicar() {
        System.out.println(getNombre() + " ladra: ¡Guau, guau!");
    }

}

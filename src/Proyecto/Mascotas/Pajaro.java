public class Pajaro extends Mascota{
    static int contador=0;
    public Pajaro(String nombre) {
        super(nombre);
    }
    
    @Override
    public void comunicar() {
        System.out.println(getNombre() + " canta: ¡Pío, pío!");
    }
}

public class Pajaro extends Mascota{
    public Pajaro(String nombre) {
        super(nombre);
    }
    
    @Override
    public void comunicar() {
        System.out.println(getNombre() + " canta: ¡Pío, pío!");
    }
}

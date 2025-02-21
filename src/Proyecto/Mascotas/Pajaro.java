public class Pajaro extends Mascota{
    public Pajaro(String nombre) {
        super(nombre);
    }

    public Pajaro(String nombre, String id, PropetarioMascota nPropetarioMascota) {
        super(nombre, id, nPropetarioMascota);
    }
    
    @Override
    public void comunicar() {
        System.out.println(getNombre() + " canta: ¡Pío, pío!");
    }
}

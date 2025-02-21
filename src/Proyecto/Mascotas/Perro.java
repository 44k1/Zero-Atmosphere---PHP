public class Perro extends Mascota{

    public Perro(String nombre, String id, PropetarioMascota nPropetarioMascota) {
        super(nombre, id, nPropetarioMascota);
    }
    
    public Perro(String nombre) {
        super(nombre);
    }
    
    @Override
    public void comunicar() {
        System.out.println(getNombre() + " ladra: ¡Guau, guau!");
    }

}

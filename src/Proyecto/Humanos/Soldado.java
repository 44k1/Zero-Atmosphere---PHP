
public class Soldado extends Entidad {
    String rango;

    public Soldado(String nombre, String rango) {
        super(nombre);
        this.rango = rango;
    }

    @Override
    public String toString() {

        return Soldado.class.getName() + " -- " + super.toString() + " Rango: " + rango;
    }
}

public class Soldado extends Entidad{
    String rango;
    
    public Soldado(String nombre, String rango){
        super(nombre);
        this.rango = rango;
    }

    @Override
    public String toString() {
        
        return  super.toString() + " Rango: " + rango;
    }
}

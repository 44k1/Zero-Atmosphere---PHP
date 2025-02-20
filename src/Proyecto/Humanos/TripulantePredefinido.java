public class TripulantePredefinido  extends Humano{
    public TripulantePredefinido(String nombre,int edad, String genero){
        super(nombre,edad,genero);
    }

    @Override
    public String toString() {
        
        return TripulantePredefinido.class.getName()+" -- " + " Nombre:" + nombre + " Edad: " + edad + " Genero: "+ genero;
    }
}

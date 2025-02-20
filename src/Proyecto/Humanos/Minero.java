

public class Minero  extends Humano {
    public Minero(){}
    public Minero(String nombre, int edad, String genero){
        super(nombre,edad,genero);
    }

    @Override
    public String toString() {
        
        return Minero.class.getName()+" -- " + " Nombre:" + nombre + " Edad: " + edad + " Genero: "+ genero +" Edad: " + edad + " Genero: " + genero;
    }
}

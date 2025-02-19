public class Humano extends Entidad {
    int edad;
    String genero;
    String rango;
    public Humano(String nombre, String id,int edad, String genero, String rango){
        super(nombre, id);
        this.edad=edad;
        this.genero=genero;
        this.rango=rango;
    }
    public Humano(){}
}

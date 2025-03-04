public class Humano extends Entidad {
    int edad;
    String genero;
    public Humano(String nombre,int edad, String genero){
        super(nombre);
        this.edad=edad;
        this.genero=genero;
        
    }
    public Humano(){}

    @Override
    public String toString() {
        //Busca el nombre de la clase humano con la funcion getName()
        return  super.toString() +" Edad: " + edad + " Genero: " + genero;
    }

}

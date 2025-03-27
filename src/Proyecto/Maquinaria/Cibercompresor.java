package Maquinaria;


public class Cibercompresor extends Maquinaria{
    int consumo;
    String sujeccion;
    double proteccion;
    public Cibercompresor(){}
    public Cibercompresor(String nombre,int consumo,String sujeccion, double proteccion){
        super(nombre);
        this.consumo=consumo;
        this.sujeccion=sujeccion;
        this.proteccion=proteccion;

    }
    @Override
    public String toString() {
        return super.toString() + " Consumo: " + consumo + " Sujeccion: " + sujeccion +" Proteccion: " + proteccion;
    }
}

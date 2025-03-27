package Maquinaria;


public class Ciberexcavadora extends Maquinaria{
    int consumo;
    String traccion;
    double proteccion;
    public Ciberexcavadora(){}
    public Ciberexcavadora(String nombre,int consumo,String traccion, double proteccion){
        super(nombre);
        this.consumo=consumo;
        this.traccion=traccion;
        this.proteccion=proteccion;
    }

    @Override
    public String toString() {
        return super.toString() + " Consumo: " + consumo + " Traccion: " + traccion +" Proteccion: " + proteccion;
    }
}

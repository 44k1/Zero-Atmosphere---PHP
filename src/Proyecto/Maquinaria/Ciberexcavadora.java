package Maquinaria;

import java.io.Serializable;

public class Ciberexcavadora extends Maquinaria implements Serializable{
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
}

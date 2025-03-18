package Maquinaria;

import java.io.Serializable;

public class Martillo extends Maquinaria implements Serializable{
    int consumo;
    String sujeccion;
    double proteccion;
    public Martillo(String nombre,int consumo,String sujeccion, double proteccion){
        super(nombre);
        this.consumo=consumo;
        this.sujeccion=sujeccion;
        this.proteccion=proteccion;
}
}

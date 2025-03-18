package Maquinaria;

import java.io.Serializable;

public class Pala extends Maquinaria implements Serializable{
    int lonmango;
    String metal;
    String proteccion;
    public Pala(String nombre,int lonmango,String metal, String proteccion){
        super(nombre);
        this.lonmango=lonmango;
        this.metal=metal;
        this.proteccion=proteccion;
    }
}

package Maquinaria;


public class Pala extends Maquinaria{
    int lonmango;
    String metal;
    String proteccion;
    public Pala(String nombre,int lonmango,String metal, String proteccion){
        super(nombre);
        this.lonmango=lonmango;
        this.metal=metal;
        this.proteccion=proteccion;
    }
    @Override
    public String toString() {
        return super.toString() + " Lonmango: " + lonmango + " Metal: " + metal +" Proteccion: " + proteccion;
    }
}

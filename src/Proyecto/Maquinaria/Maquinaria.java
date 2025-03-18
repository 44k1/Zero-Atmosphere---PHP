package Maquinaria;

import java.io.Serializable;

public abstract class Maquinaria implements Serializable{
    String nombre;
    public Maquinaria(){}
    public Maquinaria(String nombre){
        this.nombre=nombre;
    }
    @Override
    public String toString() {

        return "Nombre " + nombre;
    }
}



import java.io.Serializable;

public abstract class Maquinaria implements Serializable{
    private static final long serialVersionUID = 1L;
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

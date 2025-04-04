

import java.io.Serializable;

public abstract class Maquinaria implements Serializable{
    private static final long serialVersionUID = 1L;
    String nombre;
    public Maquinaria(){}
    public Maquinaria(String nombre){
        this.nombre=nombre;
    }
    public abstract void modificarObjeto(String valor, String valora);
    @Override
    public String toString() {

        return "Nombre " + nombre;
    }
}

public abstract class Entidad {
    String nombre;
    String id;
    static int contador = 0;
    public String toString(){
        return "Nombre: " + nombre + " ID: " + id;
    }
    public Entidad(String nombre, String id){
        this.nombre=nombre;
        this.id=id;
    }
    public Entidad(){}

    public String generarId(){
        contador++;
        return "WM" + contador;
        
    }
}


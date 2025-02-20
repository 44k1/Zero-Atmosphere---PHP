public abstract class Mascota extends Entidad {
   
    private PropietarioMascota propietario;
    
    public Mascota(String nombre) {
        super(nombre);
    }
    
    public void dormir() {
        System.out.println(nombre + " está durmiendo.");
    }
    
    public void comer() {
        System.out.println(nombre + " está comiendo.");
    }
    
    public abstract void comunicar();
    
    public void setPropietario(PropietarioMascota propietario) {
        this.propietario = propietario;
    }
    
    
    public PropietarioMascota getPropietario() {
        return propietario;
    }
}
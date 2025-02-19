public abstract class Mascota extends Entidad {
    PropetarioMascota nPropetarioMascota;
    
    public Mascota(String nombre, String id, PropetarioMascota nPropetarioMascota){
        super(nombre, id);
        this.nPropetarioMascota = nPropetarioMascota;
    }
}


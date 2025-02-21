public class ResponsableVehiculo extends Entidad {
    String direccion;
    static int contador=0;
    private String telefono;
    String idResponsable;
    String nombreResponsable;
    String nombreVehiculo;
    String idVehiculo;
//HAY QUE REPLANTEARLO (Incompleto)
    public ResponsableVehiculo(String nombre, String id,String nombreVehiculo, String idVehiculo, String direccion, String telefono) {
        idResponsable = id;
        nombreResponsable = nombre;
        this.nombreVehiculo = nombreVehiculo;
        this.idVehiculo = idVehiculo;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
   
    
    public String getDireccion() {
        return direccion;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    @Override
    public String toString() {
       
        return ResponsableVehiculo.class.getName()+ " -- " + super.toString() + " Telefono: " + telefono + " Direccion: " + direccion;
    }
}


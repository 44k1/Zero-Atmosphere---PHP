public class PropietarioMascota extends Entidad {
    String direccion;
    private String telefono;
    public PropietarioMascota(String nombre, String direccion, String telefono) {
        super(nombre);
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    @Override
    public String toString() {
       
        return PropietarioMascota.class.getName()+ " -- " + super.toString() + " Telefono: " + telefono + " Direccion: " + direccion;
    }
}


public abstract class Entidad {
    String nombre;
    String id;
    
    // Contadores para cada clase
    private static int contadorSoldado = 0;
    private static int contadorMinero = 0;
    private static int contadorPropietarioMascota = 0;
    private static int contadorResponsableVehiculo = 0;
    private static int contadorWesternMoon = 0;
    private static int contadorHumano = 0;
    private static int contadorTripulantePredefinido = 0;
    private static int contadorGato = 0;
    private static int contadorMascota = 0;
    private static int contadorPajaro = 0;
    private static int contadorPerro = 0;
    private static int contadorAeroBikes = 0;
    private static int contadorAeroCars = 0;
    private static int contadorMedioTransporte = 0;
    private static int contadorNave = 0;
    private static int contadorTurboJets = 0;

    

    public String toString(){
        return this.getClass().getSimpleName()+ " -- " +" Nombre: " + nombre + " ID: " + id;
    }
    public Entidad(String nombre){
        this.nombre=nombre;
        this.id=generarId();
    }
    public Entidad(){}

    private String generarId() {
        if (this instanceof Soldado) {
            contadorSoldado++;
            return "SOL" + contadorSoldado;
        } else if (this instanceof Minero) {
            contadorMinero++;
            return "MIN" + contadorMinero;
        } else if (this instanceof PropietarioMascota) {
            contadorPropietarioMascota++;
            return "PRM" + contadorPropietarioMascota;
        } else if (this instanceof ResponsableVehiculo) {
            contadorResponsableVehiculo++;
            return "RSV" + contadorResponsableVehiculo;
        } 
        else if (this instanceof Humano) {
            contadorHumano++;
            return "HUM" + contadorHumano;
        } else if (this instanceof TripulantePredefinido) {
            contadorTripulantePredefinido++;
            return "TPR" + contadorTripulantePredefinido;
        } else if (this instanceof Gato) {
            contadorGato++;
            return "GAT" + contadorGato;
        } else if (this instanceof Mascota) {
            contadorMascota++;
            return "MSC" + contadorMascota;
        } else if (this instanceof Pajaro) {
            contadorPajaro++;
            return "PAJ" + contadorPajaro;
        } else if (this instanceof Perro) {
            contadorPerro++;
            return "PER" + contadorPerro;
        } else if (this instanceof AeroBikes) {
            contadorAeroBikes++;
            return "ABK" + contadorAeroBikes;
        } else if (this instanceof AeroCars) {
            contadorAeroCars++;
            return "ACR" + contadorAeroCars;
        } else if (this instanceof MedioTransporte) {
            contadorMedioTransporte++;
            return "MTT" + contadorMedioTransporte;
        } else if (this instanceof Nave) {
            contadorNave++;
            return "NAV" + contadorNave;
        } else if (this instanceof TurboJets) {
            contadorTurboJets++;
            return "TJT" + contadorTurboJets;
        }
        contadorWesternMoon++;
        return "WM" + contadorWesternMoon; // Devuelve algo default
    }

    public String getNombre() {
        return nombre;
    }
    public String getId() {
        return nombre;
    }
    public String getClassName(){
        return this.getClass().getSimpleName();
    }
    /* public String getSubstringName() {
        String name = this.getClass().getSimpleName();
        return name.length() >= 3 ? name.substring(0, 3).toUpperCase() : name.toUpperCase();
    }*/
    
}




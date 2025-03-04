public abstract class MedioTransporte extends Entidad{
    int numPlazas;
    String tipoCombustible;
    int maxCombustible;
    int kilometraje;
    
    ResponsableVehiculo rVehiculo;
    public MedioTransporte(){}
    public MedioTransporte(String nombre,int numPlazas,String tipoCombustible, int maxCombustible,int kilometraje,ResponsableVehiculo rVehiculo){
        super(nombre);
        this.numPlazas=numPlazas;
        this.tipoCombustible=tipoCombustible;
        this.maxCombustible=maxCombustible;
        this.kilometraje=kilometraje;
    }
    public void rellenarDeposito(int litros){}
    public void calcularAutonomia(){}

    public void setResponsableVehiculo(ResponsableVehiculo rVehiculo) {
        this.rVehiculo = rVehiculo;
    }
    
    
    
    public ResponsableVehiculo getResponsableVehiculo() {
        return rVehiculo;
    }
}

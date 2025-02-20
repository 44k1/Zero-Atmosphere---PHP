public abstract class MedioTransporte extends Entidad{
    int numPlazas;
    String tipoCombustible;
    int maxCombustible;
    int kilometraje;
    ResponsableVehiculo rVehiculo;
    public MedioTransporte(){}
    public MedioTransporte(int numPlazas,String tipoCombustible, int maxCombustible,int kilometraje,ResponsableVehiculo rVehiculo){
        this.numPlazas=numPlazas;
        this.tipoCombustible=tipoCombustible;
        this.maxCombustible=maxCombustible;
        this.kilometraje=kilometraje;
    }
    public void rellenarDeposito(int litros){}
    public void calcularAutonomia(){}
}

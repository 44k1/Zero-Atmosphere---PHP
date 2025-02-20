public class AeroBikes extends MedioTransporte{
    int numPropulsores;
    int numAlas;
    boolean esDeCombate;
    public AeroBikes(){}
    public AeroBikes(int numPlazas,String tipoCombustible, int maxCombustible,int kilometraje,ResponsableVehiculo rVehiculo, int numPropulsores,int numAlas,boolean esDeCombate){
        super(numPlazas, tipoCombustible,  maxCombustible, kilometraje,rVehiculo);
        this.numPropulsores=numPropulsores;
        this.esDeCombate=esDeCombate;
    }
}

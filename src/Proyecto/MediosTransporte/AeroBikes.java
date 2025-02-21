public class AeroBikes extends MedioTransporte{
    static int contador=0;
    int numPropulsores;
    int numAlas;
    boolean esDeCombate;
    public AeroBikes(){}
    public AeroBikes(String nombre,int numPlazas,String tipoCombustible, int maxCombustible,int kilometraje,ResponsableVehiculo rVehiculo, int numPropulsores,int numAlas,boolean esDeCombate){
        super(nombre,numPlazas, tipoCombustible,  maxCombustible, kilometraje,rVehiculo);
        this.numPropulsores=numPropulsores;
        this.esDeCombate=esDeCombate;
    }
}

public class AeroCars extends MedioTransporte{
    int numPropulsores;
    int caballosDePotencia;
    boolean esDeCombate;
    public AeroCars(){}
    public AeroCars(int numPlazas,String tipoCombustible, int maxCombustible,int kilometraje,ResponsableVehiculo rVehiculo,int numPropulsores,int caballosDePotencia,boolean esDeCombate){
        super(numPlazas, tipoCombustible,  maxCombustible, kilometraje,rVehiculo);
        this.numPropulsores=numPropulsores;
        this.caballosDePotencia=caballosDePotencia;
        this.esDeCombate=esDeCombate;
    }
}

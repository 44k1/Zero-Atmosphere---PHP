public class AeroCars extends MedioTransporte{
    int numPropulsores;
    int caballosDePotencia;
    boolean esDeCombate;
    final Float precioYurs = (float) 12;
    public AeroCars(){}

    public AeroCars(int numPlazas,String tipoCombustible, int maxCombustible,int kilometraje,ResponsableVehiculo rVehiculo,int numPropulsores,int caballosDePotencia,boolean esDeCombate){
        super(numPlazas, tipoCombustible,  maxCombustible, kilometraje,rVehiculo);
        this.numPropulsores=numPropulsores;
        this.caballosDePotencia=caballosDePotencia;
        this.esDeCombate=esDeCombate;
    }
    public Float calculaCarburante(Float distanciaAñosLuz){
        //Funcion para realizar la raiz cuadrada
        float calculo= (float) Math.sqrt(distanciaAñosLuz) * precioYurs;
        return calculo;
    }

    public String metodoTransmision(Float distanciaAñosLuz){
        if(distanciaAñosLuz < 1){
            return "Economico";
        }else return "Estelar";
    }
}

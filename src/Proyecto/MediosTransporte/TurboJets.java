public class TurboJets extends MedioTransporte {

    // Atributos
    int numAlas;
    float caballosDePotencia;
    boolean esDeCombate;

    // Constructores
    public TurboJets() {
    }

    public TurboJets(int numAlas, float caballosDePotencia, boolean esDeCombate) {
        this.numAlas = numAlas;
        this.caballosDePotencia = caballosDePotencia;
        this.esDeCombate = esDeCombate;
    }

    // Getters y Setters
    public int getNumAlas() {
        return numAlas;
    }

    public float getCaballosDePotencia() {
        return caballosDePotencia;
    }

    public boolean getEsDeCombate() {
        return esDeCombate;
    }

    public void setNumAlas(int numAlas) {
        this.numAlas = numAlas;
    }

    public void setCaballosDePotencia(float caballosDePotencia) {
        this.caballosDePotencia = caballosDePotencia;
    }

    public void setEsDeCombate(boolean esDeCombate) {
        this.esDeCombate = esDeCombate;
    }

}

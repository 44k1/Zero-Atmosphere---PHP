


public class Ciberexcavadora extends Maquinaria{
    private static final long serialVersionUID = -5344121843752891444L;
    int consumo;
    String traccion;
    double proteccion;
    public Ciberexcavadora(){}
    public Ciberexcavadora(String nombre,int consumo,String traccion, double proteccion){
        super(nombre);
        this.consumo=consumo;
        this.traccion=traccion;
        this.proteccion=proteccion;
    }
    @Override
    public void modificarObjeto(String numeroAtributo, String valorAModificar){
        switch (numeroAtributo) {
            case "2":
            try{
                this.consumo = Integer.parseInt(valorAModificar);
            } catch (Exception e) {
               System.out.println("Error --->" +e.getMessage());
            }
                
                break;
            case "4":
            try{
                this.proteccion = Double.parseDouble(valorAModificar);
            } catch (Exception e) {
               System.out.println("Error --->" +e.getMessage());
            }
           
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Consumo: " + consumo + " Traccion: " + traccion +" Proteccion: " + proteccion;
    }
}

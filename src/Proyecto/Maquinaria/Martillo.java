
public class Martillo extends Maquinaria{
    private static final long serialVersionUID = 646472319668868050L;
    int consumo;
    String sujeccion;
    double proteccion;
    public Martillo(String nombre,int consumo,String sujeccion, double proteccion){
        super(nombre);
        this.consumo=consumo;
        this.sujeccion=sujeccion;
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
        return super.toString() + " Consumo: " + consumo + " Sujeccion: " + sujeccion +" Proteccion: " + proteccion;
    }
}

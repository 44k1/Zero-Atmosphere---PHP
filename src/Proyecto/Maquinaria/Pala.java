

public class Pala extends Maquinaria{
    private static final long serialVersionUID = -4796454839011390330L;
    int lonmango;
    String metal;
    String proteccion;
    public Pala(String nombre,int lonmango,String metal, String proteccion){
        super(nombre);
        this.lonmango=lonmango;
        this.metal=metal;
        this.proteccion=proteccion;
    }

    @Override
    public void modificarObjeto(String numeroAtributo, String valorAModificar){
        switch (numeroAtributo) {
            case "2":
            try{
                this.lonmango = Integer.parseInt(valorAModificar);
            } catch (Exception e) {
               System.out.println("Error --->" +e.getMessage());
            }
                
                break;
            case "4":
            try{
                this.proteccion = valorAModificar;
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
        return super.toString() + " Lonmango: " + lonmango + " Metal: " + metal +" Proteccion: " + proteccion;
    }
}

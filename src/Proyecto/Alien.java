public abstract class Alien extends Entidad {

    public Alien() {
        super();
        this.nombre = this.getClassName();
    }
}

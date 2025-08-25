public class Chasis {
    private String codigo;

    public Chasis(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    @Override
    public String toString() {
        return codigo;
    }
}

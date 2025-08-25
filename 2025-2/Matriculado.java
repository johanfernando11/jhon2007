public class Matriculado {
    private boolean estado;

    public Matriculado(boolean estado) {
        this.estado = estado;
    }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    @Override
    public String toString() {
        return estado ? "MATRICULADO" : "NO MATRICULADO";
    }
}

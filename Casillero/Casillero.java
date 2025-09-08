public class Casillero {
    private final int fila;
    private final int col;
    private final Tamano tamano;
    private Paquete paquete; // null si está libre

    public Casillero(int fila, int col, Tamano tamano) {
        this.fila = fila;
        this.col = col;
        this.tamano = tamano;
    }

    public int getFila() { return fila; }
    public int getCol() { return col; }
    public Tamano getTamano() { return tamano; }
    public boolean estaOcupado() { return paquete != null; }
    public Paquete getPaquete() { return paquete; }

    public void ocupar(Paquete p) {
        if (estaOcupado()) throw new IllegalStateException("Casillero ya ocupado.");
        this.paquete = p;
    }

    public void liberar() { this.paquete = null; }

    public String id() { return "R" + fila + "C" + col; }

    @Override
    public String toString() {
        return "Casillero{" +
                "id=" + id() +
                ", tamano=" + tamano +
                ", estado=" + (estaOcupado() ? "OCUPADO" : "LIBRE") +
                (estaOcupado() ? (", paquete=" + paquete.getCodigo()) : "") +
                '}';
    }
}

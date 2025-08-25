public class Modelo {
    private int anio;

    public Modelo(int anio) {
        this.anio = anio;
    }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    @Override
    public String toString() {
        return String.valueOf(anio);
    }
}

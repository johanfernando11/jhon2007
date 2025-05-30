package momento3;

public class Arma {
    private String nombre;
    private int danoMinimo;
    private int danoMaximo;

    public Arma(String nombre, int danoMinimo, int danoMaximo) {
        this.nombre = nombre;
        this.danoMinimo = danoMinimo;
        this.danoMaximo = danoMaximo;
    }

    public String getNombre() {
        return nombre;
    }

    public int generarDano() {
        return (int)(Math.random() * (danoMaximo - danoMinimo + 1)) + danoMinimo;
    }
}

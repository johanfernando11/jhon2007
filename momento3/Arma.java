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

    // armas 
    public static final Arma ESPADA_HIERRO = new Arma("Espada de Hierro", 20, 40);
    public static final Arma HACHA_GUERRA = new Arma("Hacha de Guerra", 25, 50);
    public static final Arma BASTON_FUEGO = new Arma("Bastón de Fuego", 10, 35);
    public static final Arma ORBE_ARCANO = new Arma("Orbe Arcano", 15, 30);
    public static final Arma ARCO_LARGO = new Arma("Arco Largo", 15, 35);
    public static final Arma BALLESTA_LIGERA = new Arma("Ballesta Ligera", 10, 30);
}

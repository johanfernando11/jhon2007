package momento3;

public class Guerrero extends Personaje {
    public Guerrero(String nombre) {
        super(nombre);
    }

    @Override
    public void atacar(Personaje oponente) {
        int dano = arma != null ? arma.generarDano() : 10;
        String nombreArma = arma != null ? arma.getNombre() : "sus puños";
        oponente.recibirDano(dano);
        System.out.println(nombre + " ataca con " + nombreArma + " a " + oponente.getNombre() + ", causando " + dano + " de daño.");
    }
}

package momento3;

public class Arquero extends Personaje {
    public Arquero(String nombre) {
        super(nombre);
    }

    @Override
    public void atacar(Personaje oponente) {
        int dano = arma != null ? arma.generarDano() : 10;
        String nombreArma = arma != null ? arma.getNombre() : "arco improvisado";
        oponente.recibirDano(dano);
        System.out.println(nombre + " dispara con " + nombreArma + " a " + oponente.getNombre() + ", causando " + dano + " de daño.");
    }
}

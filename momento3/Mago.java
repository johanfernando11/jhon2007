package momento3;

public class Mago extends Personaje {
    public Mago(String nombre) {
        super(nombre);
    }

    @Override
    public void atacar(Personaje oponente) {
        int dano = arma != null ? arma.generarDano() : 10;
        String nombreArma = arma != null ? arma.getNombre() : "magia básica";
        oponente.recibirDano(dano);
        System.out.println(nombre + " lanza un hechizo con " + nombreArma + " a " + oponente.getNombre() + ", causando " + dano + " de daño.");
    }
}

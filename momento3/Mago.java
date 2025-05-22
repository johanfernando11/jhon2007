package momento3;
public class Mago extends Personaje {
    public Mago(String nombre) {
        super(nombre);
    }

    @Override
    public void atacar(Personaje oponente) {
        int dano = (int)(Math.random() * 31 + 5); // daño entre 5 y 35
        oponente.recibirDano(dano);
        System.out.println(nombre + " lanza un hechizo a " + oponente.getNombre() + " causando " + dano + " de daño.");
    }
}

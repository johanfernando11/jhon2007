package momento3;
public class Arquero extends Personaje {
    public Arquero(String nombre) {
        super(nombre);
    }

    @Override
    public void atacar(Personaje oponente) {
        int dano = (int)(Math.random() * 16 + 15); // daño entre 15 y 30
        oponente.recibirDano(dano);
        System.out.println(nombre + " dispara una flecha a " + oponente.getNombre() + " causando " + dano + " de daño.");
    }
}

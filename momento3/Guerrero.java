package momento3;
public class Guerrero extends Personaje {
    public Guerrero(String nombre) {
        super(nombre);
    }

    @Override
    public void atacar(Personaje oponente) {
        int dano = (int)(Math.random() * 21 + 20); // daño entre 20 y 40
        oponente.recibirDano(dano);
        System.out.println(nombre + " lanza un espadazo a " + oponente.getNombre() + " causando " + dano + " de daño.");
    }
}

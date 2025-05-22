package momento3;
public abstract class Personaje {
    protected String nombre;
    protected int puntosDeVida = 100;

    public Personaje(String nombre) {
        this.nombre = nombre;
    }

    public abstract void atacar(Personaje oponente);

    public void recibirDano(int dano) {
        puntosDeVida -= dano;
        if (puntosDeVida < 0) puntosDeVida = 0;
    }

    public boolean estaVivo() {
        return puntosDeVida > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosDeVida() {
        return puntosDeVida;
    }
}

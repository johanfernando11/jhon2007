package momento3;

public abstract class Personaje {
    protected String nombre;
    protected int puntosDeVida = 100;
    protected Arma arma;

    public Personaje(String nombre) {
        this.nombre = nombre;
    }

    public void equiparArma(Arma arma) {
        this.arma = arma;
    }

    public Arma getArma() {
        return arma;
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
//es la clase base de todos los luchadores
//tiene métodos para recibir daño, verificar si esta vivo, obtener su nombre y puntos de vida

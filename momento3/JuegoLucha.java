package momento3;
import java.util.Scanner;

public class JuegoLucha {
    private Personaje jugador1;
    private Personaje jugador2;

    public JuegoLucha(Personaje jugador1, Personaje jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public void iniciarPelea() {
        System.out.println("¡Empieza la batalla entre " + jugador1.getNombre() + " y " + jugador2.getNombre() + "!");
        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            turno(jugador1, jugador2);
            if (jugador2.estaVivo()) {
                turno(jugador2, jugador1);
            }
        }
        System.out.println(jugador1.estaVivo() ? jugador1.getNombre() + " ganó." : jugador2.getNombre() + " ganó.");
    }

    private void turno(Personaje atacante, Personaje defensor) {
        System.out.println("\nTurno de " + atacante.getNombre() + " (HP: " + atacante.getPuntosDeVida() + ")");
        atacante.atacar(defensor);
        System.out.println(defensor.getNombre() + " ahora tiene " + defensor.getPuntosDeVida() + " puntos de vida.");
    }

   public static Personaje crearPersonaje(String nombre, int tipo) {
    switch (tipo) {
        case 1:
            return new Guerrero(nombre);
        case 2:
            return new Mago(nombre);
        case 3:
            return new Arquero(nombre);
        default:
            throw new IllegalArgumentException("Tipo inválido: " + tipo);
    }
}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nombre del Jugador 1: ");
            String nombre1 = scanner.nextLine();
            System.out.print("Tipo (1=Guerrero, 2=Mago, 3=Arquero): ");
            int tipo1 = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            System.out.print("Nombre del Jugador 2: ");
            String nombre2 = scanner.nextLine();
            System.out.print("Tipo (1=Guerrero, 2=Mago, 3=Arquero): ");
            int tipo2 = scanner.nextInt();

            Personaje p1 = crearPersonaje(nombre1, tipo1);
            Personaje p2 = crearPersonaje(nombre2, tipo2);

            JuegoLucha juego = new JuegoLucha(p1, p2);
            juego.iniciarPelea();
        }
    }
}

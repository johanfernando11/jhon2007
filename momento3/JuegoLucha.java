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
        System.out.println("\n Resultado final:");
        System.out.println(jugador1.getNombre() + ": " + jugador1.getPuntosDeVida() + " HP");
        System.out.println(jugador2.getNombre() + ": " + jugador2.getPuntosDeVida() + " HP");
        System.out.println(jugador1.estaVivo() ? jugador1.getNombre() + " ganó la batalla " : jugador2.getNombre() + " ganó la batalla ");
    }

    private void turno(Personaje atacante, Personaje defensor) {
        System.out.println("\n▶ Turno de " + atacante.getNombre() + " (HP: " + atacante.getPuntosDeVida() + ")");
        atacante.atacar(defensor);
        System.out.println(defensor.getNombre() + " ahora tiene " + defensor.getPuntosDeVida() + " puntos de vida.");
    }

    public static Personaje crearPersonaje(String nombre, int tipo) {
        switch (tipo) {
            case 1: return new Guerrero(nombre);
            case 2: return new Mago(nombre);
            case 3: return new Arquero(nombre);
            default: throw new IllegalArgumentException("Tipo inválido: " + tipo);
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Jugador 1
            System.out.print("Nombre del Jugador 1: ");
            String nombre1 = scanner.nextLine();
            System.out.print("Tipo (1=Guerrero, 2=Mago, 3=Arquero): ");
            int tipo1 = scanner.nextInt();
            scanner.nextLine();

            Personaje p1 = crearPersonaje(nombre1, tipo1);
            System.out.println("Selecciona el arma para " + nombre1 + ":");
            if (p1 instanceof Guerrero) {
                System.out.println("1. Espada de Hierro\n2. Hacha de Guerra");
                int armaElegida = scanner.nextInt();
                p1.equiparArma(armaElegida == 2 ? Arma.HACHA_GUERRA : Arma.ESPADA_HIERRO);
            } else if (p1 instanceof Mago) {
                System.out.println("1. Bastón de Fuego\n2. Orbe Arcano");
                int armaElegida = scanner.nextInt();
                p1.equiparArma(armaElegida == 2 ? Arma.ORBE_ARCANO : Arma.BASTON_FUEGO);
            } else if (p1 instanceof Arquero) {
                System.out.println("1. Arco Largo\n2. Ballesta Ligera");
                int armaElegida = scanner.nextInt();
                p1.equiparArma(armaElegida == 2 ? Arma.BALLESTA_LIGERA : Arma.ARCO_LARGO);
            }

            scanner.nextLine();

            // Jugador 2
            System.out.print("Nombre del Jugador 2: ");
            String nombre2 = scanner.nextLine();
            System.out.print("Tipo (1=Guerrero, 2=Mago, 3=Arquero): ");
            int tipo2 = scanner.nextInt();
            scanner.nextLine();

            Personaje p2 = crearPersonaje(nombre2, tipo2);
            System.out.println("Selecciona el arma para " + nombre2 + ":");
            if (p2 instanceof Guerrero) {
                System.out.println("1. Espada de Hierro\n2. Hacha de Guerra");
                int armaElegida = scanner.nextInt();
                p2.equiparArma(armaElegida == 2 ? Arma.HACHA_GUERRA : Arma.ESPADA_HIERRO);
            } else if (p2 instanceof Mago) {
                System.out.println("1. Bastón de Fuego\n2. Orbe Arcano");
                int armaElegida = scanner.nextInt();
                p2.equiparArma(armaElegida == 2 ? Arma.ORBE_ARCANO : Arma.BASTON_FUEGO);
            } else if (p2 instanceof Arquero) {
                System.out.println("1. Arco Largo\n2. Ballesta Ligera");
                int armaElegida = scanner.nextInt();
                p2.equiparArma(armaElegida == 2 ? Arma.BALLESTA_LIGERA : Arma.ARCO_LARGO);
            }

            JuegoLucha juego = new JuegoLucha(p1, p2);
            juego.iniciarPelea();
        }
    }
}
//es el motor del juego.
//tiene dos pesonajes jugador1 y jugador2
//el metodo iniciarPelea se encarga de ejecutar los turnos de combate hasta que uno de los dos pierda toda su vida
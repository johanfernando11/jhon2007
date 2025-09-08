import java.util.Optional;
import java.util.Scanner;

public class CasilleroApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventarioCasilleros inventario = new InventarioCasilleros();

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero(sc, "Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarPaquete(sc, inventario);
                    break;
                case 2:
                    consultarDisponibles(inventario);
                    break;
                case 3:
                    infoPaquete(sc, inventario);
                    break;
                case 4:
                    System.out.println(">>> Cerrando aplicación. ¡Hasta luego!");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.\n");
            }
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== MENÚ PRINCIPAL AMAZON =====");
        System.out.println("1. Registrar paquete en casillero");
        System.out.println("2. Consultar casilleros disponibles");
        System.out.println("3. Ver información de un paquete");
        System.out.println("4. Salir");
    }

    private static void registrarPaquete(Scanner sc, InventarioCasilleros inventario) {
        System.out.println("\n>>> Registro de paquete");
        String codigo = leerTexto(sc, "Código del paquete: ");
        String destinatario = leerTexto(sc, "Destinatario: ");

        Tamano tamano = null;
        while (tamano == null) {
            String t = leerTexto(sc, "Tamaño (S/M/L): ");
            tamano = Tamano.fromString(t);
            if (tamano == null) System.out.println("Tamaño inválido. Use S, M o L.");
        }

        Paquete p = new Paquete(codigo, destinatario, tamano);
        Optional<Casillero> asignado = inventario.registrarPaquete(p);

        if (asignado.isPresent()) {
            Casillero cas = asignado.get();
            System.out.println("Paquete registrado con éxito.");
            System.out.println("Casillero asignado: " + cas.id() + " | Tamaño casillero: " + cas.getTamano());
        } else {
            System.out.println("No hay casilleros disponibles adecuados para el tamaño del paquete.");
        }

        inventario.mostrarEsquema();
    }

    private static void consultarDisponibles(InventarioCasilleros inventario) {
        inventario.mostrarEsquema();
        inventario.resumenDisponibilidad();
    }

    private static void infoPaquete(Scanner sc, InventarioCasilleros inventario) {
        System.out.println("\n>>> Información de paquete");
        String codigo = leerTexto(sc, "Ingrese el código del paquete: ");

        Optional<Paquete> p = inventario.buscarPaquetePorCodigo(codigo);
        if (p.isPresent()) {
            Paquete paq = p.get();
            System.out.println("Código: " + paq.getCodigo());
            System.out.println("Destinatario: " + paq.getDestinatario());
            System.out.println("Tamaño: " + paq.getTamano());
            System.out.println("Fecha de ingreso: " + paq.fechaIngresoFmt());
            inventario.casilleroDePaquete(codigo).ifPresent(c ->
                System.out.println("Ubicación: " + c.id() + " (Tamaño casillero: " + c.getTamano() + ")")
            );
        } else {
            System.out.println("No se encontró un paquete con ese código.");
        }
    }

    // Utilidades de entrada segura
    private static int leerEntero(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine();
            try {
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private static String leerTexto(Scanner sc, String msg) {
        System.out.print(msg);
        String s = sc.nextLine();
        while (s == null || s.trim().isEmpty()) {
            System.out.print("No puede estar vacío. " + msg);
            s = sc.nextLine();
        }
        return s.trim();
    }
}



package miniproyecto;

import java.util.Scanner;

public class ejecutarsistema {

    static Scanner leer = new Scanner(System.in);
    static sistema s = new sistema();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Ingrese una opcion: ");
            switch (opcion) {
                case 1:
                    menuRegistrarBuque();
                    break;
                case 2:
                    menuRegistrarContenedor();
                    break;
                case 3:
                    mostrarPesoTotal();
                    break;
                case 4:
                    s.listarPorOrigen();
                    break;
                case 5:
                    System.out.println("\n  Cerrando el sistema. Hasta luego.");
                    break;
                default:
                    System.out.println("\n  [!] Opcion no valida. Intente de nuevo.");
            }
            if (opcion != 5) {
                System.out.println("\n  Presione ENTER para continuar...");
                leer.nextLine();
            }
        } while (opcion != 5);

        leer.close();
    }

    static void mostrarMenuPrincipal() {
        System.out.println("\n  ============================================");
        System.out.println("       SISTEMA DE ORGANIZACION DE CONTENEDORES");
        System.out.println("  ============================================");
        System.out.println("  1. Registrar buque");
        System.out.println("  2. Registrar contenedor");
        System.out.println("  3. Mostrar peso total de contenedores");
        System.out.println("  4. Listar contenedores agrupados por origen");
        System.out.println("  5. Salir");
        System.out.println("  --------------------------------------------");
    }

    static void menuRegistrarBuque() {
        System.out.println("\n  === REGISTRO DE BUQUE ===");

        if (!s.hayEspacioBuque()) {
            System.out.println("  [!] No hay espacio disponible. El muelle esta lleno (10/10 buques).");
            return;
        }

        int id = leerEntero("ID del buque: ");

        System.out.print("  Nombre del buque: ");
        String nombre = leer.nextLine().trim();

        System.out.print("  Procedencia: ");
        String procedencia = leer.nextLine().trim();

        int cantidad = leerEntero("Cantidad de contenedores que trae: ");

        buque b = new buque(id, nombre, procedencia, cantidad);
        s.registrarBuque(b);
    }

    static void menuRegistrarContenedor() {
        System.out.println("\n  === REGISTRO DE CONTENEDOR ===");

        if (!s.hayEspacioContenedor()) {
            System.out.println("  [!] La matriz esta llena. No hay espacio para mas contenedores.");
            return;
        }

        if (s.getTotalBuques() == 0) {
            System.out.println("  [!] No hay buques registrados. Registre un buque primero.");
            return;
        }

        s.mostrarMatriz();
        s.listarBuques();

        System.out.println();
        System.out.print("  Codigo del contenedor: ");
        String codigo = leer.nextLine().trim();

        System.out.print("  Origen del contenedor: ");
        String origen = leer.nextLine().trim();

        double peso = leerDouble("Peso del contenedor (toneladas): ");

        int idBuque = leerEntero("ID del buque al que pertenece: ");

        int fila = leerEntero("Fila donde desea colocarlo (0-9, fila 9 = piso): ");

        int columna = leerEntero("Columna donde desea colocarlo (0-9): ");

        int idContenedor = s.getProximoIdContenedor();
        contenedor c = new contenedor(idContenedor, codigo, origen, peso, idBuque);
        boolean exito = s.registrarContenedor(c, fila, columna);

        if (exito) {
            s.mostrarMatriz();
        }
    }

    static void mostrarPesoTotal() {
        double total = s.calcularPesoTotal();
        System.out.println("\n  === PESO TOTAL DE CONTENEDORES ===");
        System.out.println("  Peso total almacenado: " + total + " toneladas");
        System.out.println("  Total de contenedores en matriz: " + s.getTotalContenedores());
    }

    static int leerEntero(String mensaje) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("  " + mensaje);
                valor = Integer.parseInt(leer.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Ingrese un numero entero valido.");
            }
        }
        return valor;
    }

    static double leerDouble(String mensaje) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("  " + mensaje);
                valor = Double.parseDouble(leer.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Ingrese un numero decimal valido.");
            }
        }
        return valor;
    }
}
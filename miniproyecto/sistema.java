package miniproyecto;

public class sistema {

    private buque[] buques;
    private contenedor[][] matriz;
    private int totalBuques;
    private int totalContenedores;

    public sistema() {
        buques = new buque[10];
        matriz = new contenedor[10][10];
        totalBuques = 0;
        totalContenedores = 0;
    }

    public boolean hayEspacioBuque() {
        return totalBuques < 10;
    }

    public boolean hayEspacioContenedor() {
        return totalContenedores < 100;
    }

    public boolean existeBuque(int idBuque) {
        for (int i = 0; i < totalBuques; i++) {
            if (buques[i].getId() == idBuque) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarBuque(buque b) {
        if (!hayEspacioBuque()) {
            System.out.println("\n  [!] No hay espacio disponible para mas buques.");
            return false;
        }
        for (int i = 0; i < totalBuques; i++) {
            if (buques[i].getId() == b.getId()) {
                System.out.println("\n  [!] Ya existe un buque con ese ID.");
                return false;
            }
        }
        buques[totalBuques] = b;
        totalBuques++;
        System.out.println("\n  [OK] Buque registrado correctamente.");
        return true;
    }

    public boolean registrarContenedor(contenedor c, int fila, int columna) {
        if (fila < 0 || fila > 9 || columna < 0 || columna > 9) {
            System.out.println("\n  [!] Posicion fuera de rango. Filas y columnas deben estar entre 0 y 9.");
            return false;
        }
        if (matriz[fila][columna] != null) {
            System.out.println("\n  [!] Ese espacio ya esta ocupado. Elija otro.");
            return false;
        }
        if (!validarApilamiento(fila, columna)) {
            System.out.println("\n  [!] No puede colocar un contenedor en el aire. Primero llene la fila inferior.");
            return false;
        }
        if (!existeBuque(c.getIdBuque())) {
            System.out.println("\n  [!] El buque con ID " + c.getIdBuque() + " no existe. Registre el buque primero.");
            return false;
        }
        matriz[fila][columna] = c;
        totalContenedores++;
        System.out.println("\n  [OK] Contenedor registrado en posicion [" + fila + "][" + columna + "].");
        return true;
    }

    private boolean validarApilamiento(int fila, int columna) {
        if (fila == 9) {
            return true;
        }
        return matriz[fila + 1][columna] != null;
    }

    public double calcularPesoTotal() {
        double total = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matriz[i][j] != null) {
                    total += matriz[i][j].getPeso();
                }
            }
        }
        return total;
    }

    public void listarPorOrigen() {
        String[] origenes = new String[totalContenedores];
        int totalOrigenes = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matriz[i][j] != null) {
                    String origen = matriz[i][j].getOrigen();
                    boolean encontrado = false;
                    for (int k = 0; k < totalOrigenes; k++) {
                        if (origenes[k].equalsIgnoreCase(origen)) {
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        origenes[totalOrigenes] = origen;
                        totalOrigenes++;
                    }
                }
            }
        }

        if (totalOrigenes == 0) {
            System.out.println("\n  No hay contenedores registrados.");
            return;
        }

        System.out.println("\n  === CONTENEDORES AGRUPADOS POR ORIGEN ===");
        for (int o = 0; o < totalOrigenes; o++) {
            System.out.println("\n  Origen: " + origenes[o]);
            System.out.println("  ------------------------------------------");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (matriz[i][j] != null && matriz[i][j].getOrigen().equalsIgnoreCase(origenes[o])) {
                        System.out.println("    Posicion [" + i + "][" + j + "] -> Codigo: " + matriz[i][j].getCodigo()
                                + " | Peso: " + matriz[i][j].getPeso() + " ton | BuqueID: " + matriz[i][j].getIdBuque());
                    }
                }
            }
        }
    }

    public void mostrarMatriz() {
        System.out.println("\n  === ESQUEMA DE LA MATRIZ DE CONTENEDORES ===");
        System.out.print("       ");
        for (int j = 0; j < 10; j++) {
            System.out.printf("  C%-2d  ", j);
        }
        System.out.println();
        System.out.print("       ");
        for (int j = 0; j < 10; j++) {
            System.out.print("-------");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("  F%-2d |", i);
            for (int j = 0; j < 10; j++) {
                if (matriz[i][j] != null) {
                    System.out.printf("  [X]  ", j);
                } else {
                    System.out.printf("  [ ]  ", j);
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("  [X] = Ocupado   [ ] = Disponible");
        System.out.println("  Contenedores disponibles: " + (100 - totalContenedores) + " / 100");
        System.out.println("  Buques disponibles: " + (10 - totalBuques) + " / 10");
    }

    public void listarBuques() {
        if (totalBuques == 0) {
            System.out.println("\n  No hay buques registrados.");
            return;
        }
        System.out.println("\n  === BUQUES REGISTRADOS ===");
        for (int i = 0; i < totalBuques; i++) {
            System.out.println("  " + buques[i].toString());
        }
    }

    public int getTotalBuques() {
        return totalBuques;
    }

    public int getTotalContenedores() {
        return totalContenedores;
    }

    public int getProximoIdContenedor() {
        return totalContenedores + 1;
    }
}
import java.util.*;

public class InventarioCasilleros {
    private final Casillero[][] matriz;
    private final int filas;
    private final int cols;
    // Índice auxiliar para buscar paquete por código
    private final Map<String, Casillero> indexPorCodigo = new HashMap<>();

    /**
     * Crea un inventario con disposición heterogénea de tamaños.
     * Ejemplo 3x4 (12 casilleros), no todos iguales (cumple la consigna).
     *
     * Layout de tamaños:
     * R0: S  S  M  M
     * R1: S  M  L  L
     * R2: M  L  L  S
     */
    public InventarioCasilleros() {
        this.filas = 3;
        this.cols  = 4;
        this.matriz = new Casillero[filas][cols];

        Tamano[][] layout = new Tamano[][]{
            {Tamano.S, Tamano.S, Tamano.M, Tamano.M},
            {Tamano.S, Tamano.M, Tamano.L, Tamano.L},
            {Tamano.M, Tamano.L, Tamano.L, Tamano.S}
        };

        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < cols; c++) {
                matriz[r][c] = new Casillero(r, c, layout[r][c]);
            }
        }
    }

    /**
     * Regla de asignación:
     * - Preferir casillero del mismo tamaño del paquete.
     * - Si no hay, escalar a uno mayor (M->L, S->M o L).
     * - Nunca asignar a uno menor.
     * - Estrategia "first-fit" (primer libre que cumpla).
     */
    public Optional<Casillero> registrarPaquete(Paquete p) {
        List<Tamano> preferencia = ordenarPreferencia(p.getTamano());
        for (Tamano t : preferencia) {
            for (int r = 0; r < filas; r++) {
                for (int c = 0; c < cols; c++) {
                    Casillero cas = matriz[r][c];
                    if (!cas.estaOcupado() && cas.getTamano().prio() >= t.prio() && cas.getTamano().prio() >= p.getTamano().prio()) {
                        cas.ocupar(p);
                        indexPorCodigo.put(p.getCodigo(), cas);
                        return Optional.of(cas);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private List<Tamano> ordenarPreferencia(Tamano base) {
        // orden exacto -> mayores
        if (base == Tamano.S) return Arrays.asList(Tamano.S, Tamano.M, Tamano.L);
        if (base == Tamano.M) return Arrays.asList(Tamano.M, Tamano.L);
        return Collections.singletonList(Tamano.L);
    }

    public void mostrarEsquema() {
        System.out.println("\n=== ESQUEMA DE CASILLEROS ( . = libre | X = ocupado ) ===");
        for (int r = 0; r < filas; r++) {
            StringBuilder linea = new StringBuilder();
            for (int c = 0; c < cols; c++) {
                Casillero cas = matriz[r][c];
                String mark = cas.estaOcupado() ? "X" : ".";
                // Ej: [S .] o [L X]
                linea.append("[").append(cas.getTamano()).append(" ").append(mark).append("] ");
            }
            System.out.println(linea.toString().trim());
        }
        System.out.println("Leyenda: tamaño (S/M/L) y estado (libre/ocupado)\n");
    }

    public void resumenDisponibilidad() {
        int libres = 0, ocupados = 0;
        int sLib=0, mLib=0, lLib=0, sOcu=0, mOcu=0, lOcu=0;

        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < cols; c++) {
                Casillero cas = matriz[r][c];
                boolean oc = cas.estaOcupado();
                if (oc) {
                    ocupados++;
                    if      (cas.getTamano() == Tamano.S) sOcu++;
                    else if (cas.getTamano() == Tamano.M) mOcu++;
                    else lOcu++;
                } else {
                    libres++;
                    if      (cas.getTamano() == Tamano.S) sLib++;
                    else if (cas.getTamano() == Tamano.M) mLib++;
                    else lLib++;
                }
            }
        }

        System.out.println("=== DISPONIBILIDAD ===");
        System.out.printf("Total casilleros: %d  | Libres: %d  | Ocupados: %d%n", (libres+ocupados), libres, ocupados);
        System.out.printf("S -> Libres: %d | Ocupados: %d%n", sLib, sOcu);
        System.out.printf("M -> Libres: %d | Ocupados: %d%n", mLib, mOcu);
        System.out.printf("L -> Libres: %d | Ocupados: %d%n%n", lLib, lOcu);
    }

    public Optional<Paquete> buscarPaquetePorCodigo(String codigo) {
        Casillero cas = indexPorCodigo.get(codigo);
        if (cas != null && cas.estaOcupado() && cas.getPaquete().getCodigo().equals(codigo)) {
            return Optional.of(cas.getPaquete());
        }
        // fallback escaneo (por si cambias la estructura)
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < cols; c++) {
                Casillero cc = matriz[r][c];
                if (cc.estaOcupado() && cc.getPaquete().getCodigo().equalsIgnoreCase(codigo)) {
                    return Optional.of(cc.getPaquete());
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Casillero> casilleroDePaquete(String codigo) {
        Casillero cas = indexPorCodigo.get(codigo);
        if (cas != null && cas.estaOcupado() && cas.getPaquete().getCodigo().equals(codigo)) {
            return Optional.of(cas);
        }
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < cols; c++) {
                Casillero cc = matriz[r][c];
                if (cc.estaOcupado() && cc.getPaquete().getCodigo().equalsIgnoreCase(codigo)) {
                    return Optional.of(cc);
                }
            }
        }
        return Optional.empty();
    }
}

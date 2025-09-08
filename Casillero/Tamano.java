public enum Tamano {
    S(1), M(2), L(3);

    private final int prioridad;
    Tamano(int p) { this.prioridad = p; }
    public int prio() { return prioridad; }

    public static Tamano fromString(String s) {
        if (s == null) return null;
        s = s.trim().toUpperCase();
        switch (s) {
            case "S": return S;
            case "M": return M;
            case "L": return L;
            default:  return null;
        }
    }
}


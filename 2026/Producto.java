public abstract class Producto {
    private int numero;

    public Producto(int numero) {
        this.numero = numero;
    }

    // Getters y Setters
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    protected abstract int getPrecio();

    protected abstract int getCantidadstock();

    protected abstract String getNombre();

    protected abstract int getId();

    protected abstract void setCantidadstock(int nuevaCantidad);
}

package momento2;

public abstract class Producto {
    protected String nombre;
    protected double precioUnitario;

    public Producto(String nombre, double precioUnitario) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

    public abstract double calcularPrecioTotal(int cantidad);

    public String getNombre() {
        return nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }
}

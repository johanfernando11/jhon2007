package momento2;

public class Fotocopia extends Producto {
    public Fotocopia() {
        super("Fotocopia", 150);
    }

    @Override
    public double calcularPrecioTotal(int cantidad) {
        return cantidad * precioUnitario;
    }
}

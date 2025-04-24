package momento2;

public class Camara extends Producto {
    public Camara() {
        super("Cámara", 350000);
    }

    @Override
    public double calcularPrecioTotal(int cantidad) {
        return cantidad * precioUnitario;
    }
}

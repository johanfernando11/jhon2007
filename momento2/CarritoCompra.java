package momento2;

import java.util.HashMap;
import java.util.Map;

public class CarritoCompra {
    private Map<String, Double> resumen = new HashMap<>();
    private double total = 0;

    public void agregarProducto(Producto producto, int cantidad) {
        double subtotal = producto.calcularPrecioTotal(cantidad);
        resumen.put(producto.getNombre(), subtotal);
        total += subtotal;
    }

    public void mostrarResumen(String nombreCliente) {
        System.out.println("\n--- FACTURA PARA: " + nombreCliente.toUpperCase() + " ---");
        for (Map.Entry<String, Double> entry : resumen.entrySet()) {
            System.out.println("Producto: " + entry.getKey() + " - Subtotal: $" + entry.getValue());
        }
        System.out.println("TOTAL A PAGAR: $" + total);
    }
}

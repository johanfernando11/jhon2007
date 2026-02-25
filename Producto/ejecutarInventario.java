package Producto;

public class ejecutarInventario {
    public static void main(String[] args) {

        inventario inv = new inventario(10);

        inv.agregarProducto(new Producto(1, "Laptop", 1500.0, 10));
        inv.agregarProducto(new Producto(2, "Smartphone", 800.0, 3));
        inv.agregarProducto(new Producto(3, "Tablet", 400.0, 8));

        System.out.println("Inventario inicial:");
        System.out.println(inv);

        inv.actualizarStock(2, 2);
        System.out.println("\nInventario después de actualizar stock:");
        System.out.println(inv);

        double valorTotal = inv.generarInformeValorTotal();
        System.out.println("\nValor total del inventario: $" + valorTotal);

        Producto[] agotados = inv.obtenerProductosAgotados();
        System.out.println("\nProductos con stock menor a 5:");
        for (Producto p : agotados) {
            System.out.println(p);
        }

        inv.ordenarPorPrecioDescendente();
        System.out.println("\nInventario ordenado por precio descendente:");
        System.out.println(inv);
    }
}
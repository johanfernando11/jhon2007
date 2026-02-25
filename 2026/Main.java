import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // 1. Crear un Cliente
        Cliente cliente = new Cliente("1002345678", "Ana García");

        // 2. Crear Productos concretos
        Camara camara = new Camara(101, "Nikon", "D3500");
        
        Foto foto1 = new Foto("vacaciones_playa.jpg");
        Foto foto2 = new Foto("cumpleaños_amigo.png");
        foto1.print(); // Llamamos al método print()

        Impresion impresion = new Impresion(202, "Mate");
        impresion.addFoto(foto1);
        impresion.addFoto(foto2);

        // 3. Crear un Pedido
        Pedido pedido = new Pedido(cliente, new Date(), 123456789);

        // 4. Agregar productos al Pedido
        pedido.addProducto(camara);
        pedido.addProducto(impresion);

        // 5. Imprimir resumen
        System.out.println("\n--- Resumen del Pedido ---");
        System.out.println("Cliente: " + pedido.getCliente().getNombre());
        System.out.println("Fecha: " + pedido.getFecha());
        System.out.println("Total de productos: " + pedido.getProductos().size());
        
        // Muestra los detalles de la impresión
        Impresion impPedido = (Impresion) pedido.getProductos().get(1);
        System.out.println("Tipo de impresión: " + impPedido.getColor());
        System.out.println("Fotos a imprimir: " + impPedido.getFotos().size());
    }
}
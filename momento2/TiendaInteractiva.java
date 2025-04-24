package momento2;

import java.util.Scanner;

public class TiendaInteractiva {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarritoCompra carrito = new CarritoCompra();

        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("¿Cuántas cámaras deseas comprar? ");
        int numCamaras = scanner.nextInt();
        Producto camara = new Camara();
        carrito.agregarProducto(camara, numCamaras);

        System.out.print("¿Cuántas fotocopias deseas sacar? ");
        int numFotocopias = scanner.nextInt();
        Producto fotocopia = new Fotocopia();
        carrito.agregarProducto(fotocopia, numFotocopias);

        carrito.mostrarResumen(nombre);
        scanner.close();
    }
}

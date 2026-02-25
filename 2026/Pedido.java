import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import Producto.Producto;

public class Pedido {
    // Relación con Cliente
    private Cliente cliente; 
    // Relación con Producto[]
    private List<Producto> productos; 
    
    private Date fecha;
    private int numeroTarjetaCredito;

    public Pedido(Cliente cliente, Date fecha, int numeroTarjetaCredito) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.numeroTarjetaCredito = numeroTarjetaCredito;
        // Inicializa la lista de productos
        this.productos = new ArrayList<>(); 
    }

    // Método para añadir productos al pedido
    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    // Getters y Setters
    public Cliente getCliente() { return cliente; }
    public Date getFecha() { return fecha; }
    public int getNumeroTarjetaCredito() { return numeroTarjetaCredito; }
    public List<Producto> getProductos() { return productos; }

    // El setter de cliente se suele omitir en composiciones fuertes
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setNumeroTarjetaCredito(int numeroTarjetaCredito) { 
        this.numeroTarjetaCredito = numeroTarjetaCredito; 
    }
}
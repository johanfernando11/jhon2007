package Producto;

public class inventario {
    private Producto[] productos;
    private int cantidad;

    public inventario(int tamano) {
        this.productos = new Producto[tamano];
        this.cantidad = 0;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void agregarProducto(Producto p) {
        if (cantidad < productos.length) {
            productos[cantidad] = p;
            cantidad++;
            System.out.println("Producto agregado: " + p.getNombre());
        } else {
            System.out.println("Inventario lleno, no se puede agregar.");
        }
    }

    public Producto buscarPorId(int id) {
        for (int i = 0; i < cantidad; i++) {
            if (productos[i].getId() == id) {
                return productos[i];
            }
        }
        return null;
    }

    public void actualizarStock(int id, int nuevaCantidad) {
        Producto p = buscarPorId(id);
        if (p != null) {
            p.setCantidadstock(nuevaCantidad);
            System.out.println("Stock actualizado: " + p.getNombre() + " -> " + nuevaCantidad);
        } else {
            System.out.println("Producto con id " + id + " no encontrado.");
        }
    }

    public double generarInformeValorTotal() {
        double valorTotal = 0.0;
        for (int i = 0; i < cantidad; i++) {
            valorTotal += productos[i].getPrecio() * productos[i].getCantidadstock();
        }
        return valorTotal;
    }

    public Producto[] obtenerProductosAgotados() {
        int contador = 0;
        for (int i = 0; i < cantidad; i++) {
            if (productos[i].getCantidadstock() < 5) {
                contador++;
            }
        }
        Producto[] agotados = new Producto[contador];
        int pos = 0;
        for (int i = 0; i < cantidad; i++) {
            if (productos[i].getCantidadstock() < 5) {
                agotados[pos] = productos[i];
                pos++;
            }
        }
        return agotados;
    }

    public void ordenarPorPrecioDescendente() {
        for (int i = 0; i < cantidad - 1; i++) {
            for (int j = 0; j < cantidad - 1 - i; j++) {
                if (productos[j].getPrecio() < productos[j + 1].getPrecio()) {
                    Producto temp = productos[j];
                    productos[j] = productos[j + 1];
                    productos[j + 1] = temp;
                }
            }
        }
        System.out.println("Productos ordenados por precio descendente.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventario:\n");
        for (int i = 0; i < cantidad; i++) {
            sb.append(productos[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
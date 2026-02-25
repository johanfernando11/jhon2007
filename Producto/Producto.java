package Producto;


public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidadstock;

    public Producto(int id, String nombre, double precio, int cantidadstock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadstock = cantidadstock;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadstock() {
        return cantidadstock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidadstock(int cantidadstock) {
        this.cantidadstock = cantidadstock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidadstock=" + cantidadstock +
                '}';
    }

}

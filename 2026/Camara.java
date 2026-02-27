import Producto.Producto;

public class Camara extends Producto {
    private String marca;
    private String modelo;

    public Camara(int numero, String marca, String modelo) {
        super(numero, modelo, numero, numero);
        this.marca = marca;
        this.modelo = modelo;
    }

    // Getters y Setters
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
}

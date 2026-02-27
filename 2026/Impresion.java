import java.util.ArrayList;
import java.util.List;

import Producto.Producto;

public class Impresion extends Producto {
    private String color;
    // Relación de composición con Foto[]
    private List<Foto> fotos; 

    public Impresion(int numero, String color) {
        super(numero, color, numero, numero);
        this.color = color;
        this.fotos = new ArrayList<>();
    }
    
    // Método para manejar la colección de fotos
    public void addFoto(Foto foto) {
        this.fotos.add(foto);
    }

    // Getters y Setters
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public List<Foto> getFotos() { return fotos; }
    // En composiciones fuertes, el setter de la lista completa se suele omitir
}
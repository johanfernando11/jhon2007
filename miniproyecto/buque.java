package miniproyecto;

public class buque {

    private int id;
    private String nombre;
    private String procedencia;
    private int cantidadContenedores;

    public buque(int id, String nombre, String procedencia, int cantidadContenedores) {
        this.id = id;
        this.nombre = nombre;
        this.procedencia = procedencia;
        this.cantidadContenedores = cantidadContenedores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public int getCantidadContenedores() {
        return cantidadContenedores;
    }

    public void setCantidadContenedores(int cantidadContenedores) {
        this.cantidadContenedores = cantidadContenedores;
    }

    public String toString() {
        return "Buque [ID: " + id + " | Nombre: " + nombre + " | Procedencia: " + procedencia + " | Contenedores: " + cantidadContenedores + "]";
    }
}
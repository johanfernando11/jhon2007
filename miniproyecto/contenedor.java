package miniproyecto;

public class contenedor {

    private int id;
    private String codigo;
    private String origen;
    private double peso;
    private int idBuque;

    public contenedor(int id, String codigo, String origen, double peso, int idBuque) {
        this.id = id;
        this.codigo = codigo;
        this.origen = origen;
        this.peso = peso;
        this.idBuque = idBuque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getIdBuque() {
        return idBuque;
    }

    public void setIdBuque(int idBuque) {
        this.idBuque = idBuque;
    }

    public String toString() {
        return "C[" + codigo + "]";
    }
}
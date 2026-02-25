public class moto {
    private int modelo;
    private String marca;
    private String color;
    private double cilindraje;
    private double precio;
    public moto(int modelo, String marca, String color, double cilindraje, double precio) {
        this.modelo = modelo;
        this.marca = marca;
        this.color = color;
        this.cilindraje = cilindraje;
        this.precio = precio;
    }

    public int getmodelo() {
        return modelo;
    }

    public void setmodelo(int modelo) {
        this.modelo = modelo;
    }

    public String getmarca() {
        return marca;}    
        public void setmarca(String marca) {
        this.marca = marca;
    }

    public String getcolor() {
        return color;
    }

    public void setcolor(String color) {
        this.color = color;
    }

    public double getcilindraje() {
        return cilindraje;
    }

    public void setcilindraje(double cilindraje) {
        this.cilindraje = cilindraje;
    }

    public double getprecio() {
        return precio;
    }

    public void setprecio(double precio) {
        this.precio = precio;
    }

    public String realizarmantenimiento(String mtto) {
        return "mantenimiento realizado fue " + mtto;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "modelo=" + modelo +
                ", marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                ", cilindraje=" + cilindraje +
                ", precio=" + precio +
                '}';
    }
}
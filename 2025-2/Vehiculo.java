public class Vehiculo {
    private Chasis chasis;
    private Marca marca;
    private Modelo modelo;
    private Matriculado matriculado;

    public Vehiculo(Chasis chasis, Marca marca, Modelo modelo, Matriculado matriculado) {
        this.chasis = chasis;
        this.marca = marca;
        this.modelo = modelo;
        this.matriculado = matriculado;
    }

    public Chasis getChasis() { return chasis; }
    public Marca getMarca() { return marca; }
    public Modelo getModelo() { return modelo; }
    public Matriculado getMatriculado() { return matriculado; }

    @Override
    public String toString() {
        return "Vehiculo{chasis=" + chasis +
                ", marca=" + marca +
                ", modelo=" + modelo +
                ", matriculado=" + matriculado + "}";
    }

    public String mostrarInfo() {
        return "Chasis: " + chasis +
               " | Marca: " + marca +
               " | Modelo: " + modelo +
               " | " + matriculado;
    }
}

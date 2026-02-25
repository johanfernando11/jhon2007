public class Foto {
    private String fichero;

    public Foto(String fichero) {
        this.fichero = fichero;
    }

    // Método especificado en el diagrama
    public void print() {
        System.out.println("Imprimiendo foto del archivo: " + fichero);
    }

    // Getters y Setters
    public String getFichero() { return fichero; }
    public void setFichero(String fichero) { this.fichero = fichero; }
}
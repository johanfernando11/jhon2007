import java.util.Arrays;

public class Banco {
    private String nombre;
    private String direccion;
    private Cuenta[] cuenta;

    public Banco(String nombre, String direccion, Cuenta[] cuenta) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuenta = cuenta;
    }

    public String consultarCliente(Cliente cliente) {
        return cliente.toString();
    }

    public String toString() {
        return "Banco { Nombre: " + nombre +
                " Direccion: "  + direccion +
                 "" + Arrays.toString(cuenta) + "}";
    }

}
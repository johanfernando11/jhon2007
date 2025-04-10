public class Trabajador{
    // trabajador 

    private int idTrabajador;
    private String nombre;
    private String apellido;

    //contructor
    public Trabajador(int idTrabajador, String nombre, String apellido){
        this.idTrabajador = idTrabajador;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    //metodos getter
    public String getNombre(){
        return nombre;

    }
    public int getIdTrabajador(){
        return idTrabajador;
    }
    public String getApellido(){
        return apellido;
    }
    public double pagar(){
        return 0.0;
    }

    public String toString(){
        return "Trabajador { idtrabajador: "+ idTrabajador +
        "Nombre: "+ nombre + 
        "Apellido: "+ apellido + "}";
    }
}
package estudiantes;

public class curso {
    private int id;
    private String nombrecurso;
    private String profesor;
    private int cantidadestudiantes;

    private static int totalestudiantes = 0;

    public curso(int id, String profesor, int cantidadestudiantes, String nombrecurso) {
        this.id = id;
        this.nombrecurso = nombrecurso;
        this.profesor = profesor;
        this.cantidadestudiantes = cantidadestudiantes;
        totalestudiantes += cantidadestudiantes;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNombrecurso() {return nombrecurso;}
    public void setNombrecurso(String nombrecurso) {this.nombrecurso = nombrecurso;}

    public String getProfesor() {return profesor;}
    public void setProfesor(String profesor) {this.profesor = profesor;}

    public int getCantidadestudiantes() {return cantidadestudiantes;}
    public void setCantidadestudiantes(int cantidadestudiantes) { this.cantidadestudiantes = cantidadestudiantes;}

    public static int getTotalestudiantes() {return totalestudiantes;}

    @Override
    public String toString() {  
        return "curso{" +
                "id=" + id +
                ", nombrecurso='" + nombrecurso + '\'' +
                ", profesor='" + profesor + '\'' +
                ", cantidadestudiantes=" + cantidadestudiantes +
                '}';
    }

    public static String getTotalEstudiantes() {
        throw new UnsupportedOperationException("Unimplemented method 'getTotalEstudiantes'");
    }

} 
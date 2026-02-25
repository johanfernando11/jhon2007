package estudiantes;

public class EjecutarCurso {
    public static void main(String[] args) {
        curso[] c = new curso[5];
        c[0] = new curso(1, "Matematicas", 30, "Prof. Garcia");
        c[1] = new curso(2, "Programacion", 25, "Prof. Lopez");
        c[2] = new curso(3, "Historia", 40, "Prof. Martinez");
        c[3] = new curso(4, "Fisica", 20, "Prof. Ramirez");
        c[4] = new curso(5, "Ingles", 35, "Prof. Torres");

        System.out.println("===== LISTA DE CURSOS =====");
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }

        System.out.println("\nEl total de cursos creados es: " + c.length);

        System.out.println("El total de estudiantes en todos los cursos es: " + curso.getTotalEstudiantes());

        System.out.println("\n===== PROFESORES =====");
        for (int i = 0; i < c.length; i++) {
            System.out.println("- " + c[i].getProfesor() + " imparte: " + c[i].getNombrecurso());
        }
    }
}

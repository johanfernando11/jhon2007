public class EjecutarHerencia {
    public static void main(String[] args) {
        Trabajador[] T= new Trabajador[2];
        //cracion de los objetos (instanciar)

        T[0] = new Operario(1, "Daniel", "Castro", 135);
        T[1] = new Consultor(2, "gabriel", "Smith", 10);

        for(Trabajador x: T){
            System.out.println(x.getClass() + " - " + x.pagar());
        }


    }
}
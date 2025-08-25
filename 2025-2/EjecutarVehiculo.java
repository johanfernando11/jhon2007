public class EjecutarVehiculo {
    public static void main(String[] args) {
        // Crear un arreglo de 5 vehículos
        Vehiculo[] v = new Vehiculo[5];

        // Asignar objetos al arreglo
        v[0] = new Vehiculo("1F032", "BMW", 1950, true);
        v[1] = new Vehiculo("9Z771", "Toyota", 2018, true);
        v[2] = new Vehiculo("7A445", "Kia", 2020, false);
        v[3] = new Vehiculo("5M909", "Mazda", 2019, true);
        v[4] = new Vehiculo("3Q210", "Renault", 2022, true);

        // Usar el método iterarVehiculos de la clase Vehiculo
        System.out.println("== Listado de Vehículos ==");
        System.out.println(v[0].iterarVehiculos(v));
    }
}

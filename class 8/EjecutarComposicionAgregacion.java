public class EjecutarComposicionAgregacion {
    public static void main(String[] args) {
        
        //cracion de obj para validar los modelos 

        Cuenta[] c = new Cuenta[2];
        c[0] = new Cuenta(450, "Ahorros", 2000.0);
        c[1] = new Cuenta(451,"Corriente",3000.0);
        /*
        Cuenta objCuenta1 = new Cuenta(450, "Ahorros", 2000.0);
        Cuenta objCuenta2 = new Cuenta(451,"Corriente",3000.0);*/

        Cliente objCliente1 = new Cliente(10087026, "Bayron");
        Banco objBanco1 = new Banco("Miobanck", "cra 13 25 36", c);
        System.out.println(objBanco1);
        System.out.println(objBanco1.consultarCliente(objCliente1));






    }
}
public class Cuenta {
    private int numero;
    private String tipoCuenta;
    private double saldo;

    public Cuenta(int numero, String tipoCuenta, double saldo){
        this.numero= numero;
        this.tipoCuenta= tipoCuenta;
        this.saldo = saldo;
    }

    public String toString(){
        return "Cuenta { Numero: " + numero +
                         " Tipo: " + tipoCuenta +
                         " Saldo: " + saldo + "}";
    }
}
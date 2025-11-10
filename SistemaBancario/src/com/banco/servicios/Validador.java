package com.banco.servicios;

import com.banco.modelos.Cuenta;
import com.banco.excepciones.*;

public class Validador {
    
    public static void validarMonto(double monto) throws MontoInvalidoException {
        if (monto <= 0) {
            throw new MontoInvalidoException("El monto debe ser mayor a cero");
        }
    }
    
    public static void validarSaldoSuficiente(Cuenta cuenta, double monto) 
            throws SaldoInsuficienteException {
        if (cuenta.getSaldo() < monto) {
            throw new SaldoInsuficienteException(
                "Saldo insuficiente. Saldo actual: " + cuenta.getSaldo() + ", Requerido: " + monto
            );
        }
    }
    
    public static void validarCuentaExiste(Cuenta cuenta, String numeroCuenta) 
            throws CuentaNoEncontradaException {
        if (cuenta == null) {
            throw new CuentaNoEncontradaException("Cuenta no encontrada: " + numeroCuenta);
        }
    }
    
    public static boolean validarFormatoNumeroCuenta(String numeroCuenta) {
        return numeroCuenta != null && numeroCuenta.matches("\\d{10}");
    }
    
    public static boolean validarDocumento(String documento) {
        return documento != null && documento.matches("\\d{6,12}");
    }
}
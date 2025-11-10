package com.banco.servicios;

import com.banco.modelos.Cuenta;
import com.banco.modelos.Transaccion;
import com.banco.estructuras.GestorCuentas;
import com.banco.estructuras.HistorialTransacciones;
import com.banco.excepciones.*;
import com.banco.utilidades.GeneradorID;
import com.banco.utilidades.Logger;

public class ServicioTransacciones {
    private GestorCuentas gestorCuentas;
    private HistorialTransacciones historial;
    
    public ServicioTransacciones(GestorCuentas gestorCuentas, HistorialTransacciones historial) {
        this.gestorCuentas = gestorCuentas;
        this.historial = historial;
    }
    
    public boolean realizarDeposito(String numeroCuenta, double monto) 
            throws CuentaNoEncontradaException, MontoInvalidoException {
        
        Validador.validarMonto(monto);
        
        Cuenta cuenta = gestorCuentas.buscarCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new CuentaNoEncontradaException("Cuenta no encontrada: " + numeroCuenta);
        }
        
        cuenta.depositar(monto);
        
        Transaccion trans = new Transaccion(
            GeneradorID.generarIDTransaccion(),
            Transaccion.TipoTransaccion.DEPOSITO,
            monto,
            numeroCuenta,
            null
        );
        historial.agregar(trans);
        
        Logger.registrar("Depósito realizado: " + monto + " en cuenta " + numeroCuenta);
        return true;
    }
    
    public boolean realizarRetiro(String numeroCuenta, double monto) 
            throws CuentaNoEncontradaException, MontoInvalidoException, SaldoInsuficienteException {
        
        Validador.validarMonto(monto);
        
        Cuenta cuenta = gestorCuentas.buscarCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new CuentaNoEncontradaException("Cuenta no encontrada: " + numeroCuenta);
        }
        
        Validador.validarSaldoSuficiente(cuenta, monto);
        
        try {
            cuenta.retirar(monto);
        } catch (Exception e) {
            throw new SaldoInsuficienteException(e.getMessage());
        }
        
        Transaccion trans = new Transaccion(
            GeneradorID.generarIDTransaccion(),
            Transaccion.TipoTransaccion.RETIRO,
            monto,
            numeroCuenta,
            null
        );
        historial.agregar(trans);
        
        Logger.registrar("Retiro realizado: " + monto + " de cuenta " + numeroCuenta);
        return true;
    }
    
    public boolean realizarTransferencia(String cuentaOrigen, String cuentaDestino, double monto)
            throws CuentaNoEncontradaException, MontoInvalidoException, SaldoInsuficienteException {
        
        Validador.validarMonto(monto);
        
        Cuenta origen = gestorCuentas.buscarCuenta(cuentaOrigen);
        Cuenta destino = gestorCuentas.buscarCuenta(cuentaDestino);
        
        if (origen == null) {
            throw new CuentaNoEncontradaException("Cuenta origen no encontrada: " + cuentaOrigen);
        }
        if (destino == null) {
            throw new CuentaNoEncontradaException("Cuenta destino no encontrada: " + cuentaDestino);
        }
        
        Validador.validarSaldoSuficiente(origen, monto);
        
        try {
            origen.retirar(monto);
            destino.depositar(monto);
        } catch (Exception e) {
            throw new SaldoInsuficienteException(e.getMessage());
        }
        
        Transaccion trans = new Transaccion(
            GeneradorID.generarIDTransaccion(),
            Transaccion.TipoTransaccion.TRANSFERENCIA,
            monto,
            cuentaOrigen,
            cuentaDestino
        );
        historial.agregar(trans);
        
        Logger.registrar("Transferencia realizada: " + monto + " de " + cuentaOrigen + " a " + cuentaDestino);
        return true;
    }
}
package com.banco.estructuras;

import com.banco.modelos.Cuenta;
import java.util.ArrayList;
import java.util.List;

public class GestorCuentas {
    private List<Cuenta> cuentas;
    
    public GestorCuentas() {
        this.cuentas = new ArrayList<>();
    }
    
    public boolean agregarCuenta(Cuenta cuenta) {
        if (buscarCuenta(cuenta.getNumeroCuenta()) == null) {
            cuentas.add(cuenta);
            return true;
        }
        return false;
    }
    
    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }
    
    public boolean eliminarCuenta(String numeroCuenta) {
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            cuenta.setActiva(false);
            return true;
        }
        return false;
    }
    
    public List<Cuenta> listarCuentas() {
        return new ArrayList<>(cuentas);
    }
    
    public List<Cuenta> listarCuentasActivas() {
        List<Cuenta> activas = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.isActiva()) {
                activas.add(cuenta);
            }
        }
        return activas;
    }
    
    public int getCantidadCuentas() {
        return cuentas.size();
    }
}
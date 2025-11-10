package com.banco.modelos;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String numeroCuenta;
    private Cliente titular;
    private double saldo;
    private String tipoCuenta;
    private LocalDateTime fechaCreacion;
    private boolean activa;
    
    public Cuenta(String numeroCuenta, Cliente titular, String tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.tipoCuenta = tipoCuenta;
        this.saldo = 0.0;
        this.fechaCreacion = LocalDateTime.now();
        this.activa = true;
    }
    
    public boolean depositar(double monto) {
        if (monto > 0 && activa) {
            saldo += monto;
            return true;
        }
        return false;
    }
    
    public boolean retirar(double monto) throws Exception {
        if (monto <= 0) {
            throw new Exception("El monto debe ser positivo");
        }
        if (!activa) {
            throw new Exception("La cuenta está inactiva");
        }
        if (monto > saldo) {
            throw new Exception("Saldo insuficiente");
        }
        saldo -= monto;
        return true;
    }
    
    public String getNumeroCuenta() { return numeroCuenta; }
    public Cliente getTitular() { return titular; }
    public double getSaldo() { return saldo; }
    public String getTipoCuenta() { return tipoCuenta; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    
    @Override
    public String toString() {
        return String.format("Cuenta[%s] - Titular: %s - Saldo: $%.2f - Tipo: %s",
                numeroCuenta, titular.getNombreCompleto(), saldo, tipoCuenta);
    }
}
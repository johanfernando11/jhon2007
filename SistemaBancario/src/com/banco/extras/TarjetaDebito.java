package com.banco.extras;

import java.time.LocalDate;

public class TarjetaDebito {
    private String numeroTarjeta;
    private String numeroCuenta;
    private String pin;
    private LocalDate fechaVencimiento;
    private boolean activa;
    
    public TarjetaDebito(String numeroTarjeta, String numeroCuenta) {
        this.numeroTarjeta = numeroTarjeta;
        this.numeroCuenta = numeroCuenta;
        this.fechaVencimiento = LocalDate.now().plusYears(3);
        this.activa = true;
    }
    
    public boolean validarPIN(String pin) {
        return this.pin.equals(pin) && activa;
    }
    
    public String getNumeroTarjeta() { return numeroTarjeta; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
    public void setPIN(String pin) { this.pin = pin; }
}
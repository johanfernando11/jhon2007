package com.banco.modelos;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaccion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum TipoTransaccion {
        DEPOSITO, RETIRO, TRANSFERENCIA
    }
    
    private String idTransaccion;
    private TipoTransaccion tipo;
    private double monto;
    private LocalDateTime fecha;
    private String cuentaOrigen;
    private String cuentaDestino;
    private String descripcion;
    
    public Transaccion(String id, TipoTransaccion tipo, double monto, 
                       String cuentaOrigen, String cuentaDestino) {
        this.idTransaccion = id;
        this.tipo = tipo;
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.fecha = LocalDateTime.now();
    }
    
    public String getIdTransaccion() { return idTransaccion; }
    public TipoTransaccion getTipo() { return tipo; }
    public double getMonto() { return monto; }
    public LocalDateTime getFecha() { return fecha; }
    public String getCuentaOrigen() { return cuentaOrigen; }
    public String getCuentaDestino() { return cuentaDestino; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    @Override
    public String toString() {
        return String.format("Transacción[%s] - %s - $%.2f - %s",
                idTransaccion, tipo, monto, fecha);
    }
}
package com.banco.servicios;

import com.banco.modelos.Cuenta;
import com.banco.modelos.Transaccion;
import com.banco.estructuras.GestorCuentas;
import com.banco.estructuras.HistorialTransacciones;
import com.banco.algoritmos.AlgoritmosOrdenamiento;
import com.banco.utilidades.FormateadorDatos;
import java.util.List;

public class ServicioReportes {
    private GestorCuentas gestorCuentas;
    private HistorialTransacciones historial;
    
    public ServicioReportes(GestorCuentas gestorCuentas, HistorialTransacciones historial) {
        this.gestorCuentas = gestorCuentas;
        this.historial = historial;
    }
    
    public String generarReporteCuentas() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("\n========== REPORTE DE CUENTAS ==========\n\n");
        
        List<Cuenta> cuentas = gestorCuentas.listarCuentasActivas();
        AlgoritmosOrdenamiento.quickSortPorSaldo(cuentas, 0, cuentas.size() - 1);
        
        double saldoTotal = 0;
        for (Cuenta cuenta : cuentas) {
            reporte.append(FormateadorDatos.formatearCuenta(cuenta)).append("\n");
            saldoTotal += cuenta.getSaldo();
        }
        
        reporte.append("\n----------------------------------------\n");
        reporte.append("Total cuentas: ").append(cuentas.size()).append("\n");
        reporte.append("Saldo total: ").append(FormateadorDatos.formatearMoneda(saldoTotal)).append("\n");
        reporte.append("========================================\n");
        
        return reporte.toString();
    }
    
    public String generarReporteSaldosAltos(double limite) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("\n===== CUENTAS CON SALDO MAYOR A ")
               .append(FormateadorDatos.formatearMoneda(limite))
               .append(" =====\n\n");
        
        List<Cuenta> cuentas = gestorCuentas.listarCuentasActivas();
        int contador = 0;
        
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getSaldo() > limite) {
                reporte.append(FormateadorDatos.formatearCuenta(cuenta)).append("\n");
                contador++;
            }
        }
        
        reporte.append("\nTotal: ").append(contador).append(" cuentas\n");
        return reporte.toString();
    }
    
    public String generarReporteTransacciones() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("\n========== HISTORIAL DE TRANSACCIONES ==========\n\n");
        
        List<Transaccion> transacciones = historial.obtenerHistorial();
        
        for (Transaccion t : transacciones) {
            reporte.append(FormateadorDatos.formatearTransaccion(t)).append("\n");
        }
        
        reporte.append("\nTotal transacciones: ").append(transacciones.size()).append("\n");
        reporte.append("================================================\n");
        
        return reporte.toString();
    }
    
    public String generarReportePorCuenta(String numeroCuenta) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("\n===== TRANSACCIONES DE CUENTA ").append(numeroCuenta).append(" =====\n\n");
        
        List<Transaccion> transacciones = historial.obtenerPorCuenta(numeroCuenta);
        
        if (transacciones.isEmpty()) {
            reporte.append("No hay transacciones registradas para esta cuenta.\n");
        } else {
            for (Transaccion t : transacciones) {
                reporte.append(FormateadorDatos.formatearTransaccion(t)).append("\n");
            }
        }
        
        reporte.append("\nTotal: ").append(transacciones.size()).append(" transacciones\n");
        return reporte.toString();
    }
}
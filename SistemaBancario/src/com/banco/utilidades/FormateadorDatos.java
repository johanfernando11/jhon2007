package com.banco.utilidades;

import com.banco.modelos.Cuenta;
import com.banco.modelos.Transaccion;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormateadorDatos {
    private static final NumberFormat formatoMoneda = 
        NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
    private static final DateTimeFormatter formatoFecha = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public static String formatearMoneda(double monto) {
        return formatoMoneda.format(monto);
    }
    
    public static String formatearCuenta(Cuenta cuenta) {
        return String.format("%-12s | %-25s | Saldo: %15s | Tipo: %s",
            cuenta.getNumeroCuenta(),
            cuenta.getTitular().getNombreCompleto(),
            formatearMoneda(cuenta.getSaldo()),
            cuenta.getTipoCuenta()
        );
    }
    
    public static String formatearTransaccion(Transaccion t) {
        String fecha = t.getFecha().format(formatoFecha);
        String destino = t.getCuentaDestino() != null ? " → " + t.getCuentaDestino() : "";
        
        return String.format("[%s] %s | %s | %s | Cuenta: %s%s",
            t.getIdTransaccion(),
            fecha,
            t.getTipo(),
            formatearMoneda(t.getMonto()),
            t.getCuentaOrigen(),
            destino
        );
    }
}
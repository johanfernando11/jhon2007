package com.banco.utilidades;

import java.util.Random;

public class GeneradorID {
    private static Random random = new Random();
    private static long contadorTransacciones = 1000;
    
    public static String generarNumeroCuenta() {
        long numero = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(numero);
    }
    
    public static String generarIDTransaccion() {
        return "TRX" + String.format("%06d", contadorTransacciones++);
    }
    
    public static String generarIDCliente() {
        return "CLI" + String.format("%05d", random.nextInt(100000));
    }
}
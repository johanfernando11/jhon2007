package com.banco.utilidades;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String RUTA_LOG = "logs/sistema.log";
    private static final DateTimeFormatter formato = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static void registrar(String mensaje) {
        try {
            File directorio = new File("logs");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            
            try (PrintWriter writer = new PrintWriter(
                    new FileWriter(RUTA_LOG, true))) {
                String timestamp = LocalDateTime.now().format(formato);
                writer.println("[" + timestamp + "] " + mensaje);
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en log: " + e.getMessage());
        }
    }
    
    public static void registrarError(String mensaje, Exception e) {
        registrar("ERROR: " + mensaje + " - " + e.getMessage());
    }
}
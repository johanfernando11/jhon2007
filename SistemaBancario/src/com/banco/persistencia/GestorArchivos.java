package com.banco.persistencia;

import com.banco.modelos.Cuenta;
import com.banco.modelos.Transaccion;
import com.banco.estructuras.GestorCuentas;
import com.banco.estructuras.HistorialTransacciones;
import java.io.*;
import java.util.List;

public class GestorArchivos {
    private static final String RUTA_CUENTAS = "data/cuentas.dat";
    private static final String RUTA_TRANSACCIONES = "data/transacciones.dat";
    
    public static boolean guardarCuentas(GestorCuentas gestorCuentas) {
        try {
            File directorio = new File("data");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(RUTA_CUENTAS))) {
                oos.writeObject(gestorCuentas.listarCuentas());
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error al guardar cuentas: " + e.getMessage());
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void cargarCuentas(GestorCuentas gestorCuentas) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(RUTA_CUENTAS))) {
            List<Cuenta> cuentas = (List<Cuenta>) ois.readObject();
            for (Cuenta cuenta : cuentas) {
                gestorCuentas.agregarCuenta(cuenta);
            }
            System.out.println("Cuentas cargadas exitosamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró archivo de cuentas. Iniciando con datos vacíos.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar cuentas: " + e.getMessage());
        }
    }
    
    public static boolean guardarTransacciones(HistorialTransacciones historial) {
        try {
            File directorio = new File("data");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(RUTA_TRANSACCIONES))) {
                oos.writeObject(historial.obtenerHistorial());
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error al guardar transacciones: " + e.getMessage());
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void cargarTransacciones(HistorialTransacciones historial) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(RUTA_TRANSACCIONES))) {
            List<Transaccion> transacciones = (List<Transaccion>) ois.readObject();
            for (Transaccion t : transacciones) {
                historial.agregar(t);
            }
            System.out.println("Transacciones cargadas exitosamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró archivo de transacciones.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar transacciones: " + e.getMessage());
        }
    }
    
    public static boolean exportarReporte(String contenido, String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/" + nombreArchivo))) {
            writer.println(contenido);
            return true;
        } catch (IOException e) {
            System.err.println("Error al exportar reporte: " + e.getMessage());
            return false;
        }
    }
}
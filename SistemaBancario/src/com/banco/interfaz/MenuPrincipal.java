package com.banco.interfaz;

import com.banco.estructuras.*;
import com.banco.servicios.*;
import com.banco.persistencia.GestorArchivos;
import com.banco.utilidades.Logger;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;
    private GestorCuentas gestorCuentas;
    private HistorialTransacciones historial;
    private ColaTransacciones colaTransacciones;
    private ServicioTransacciones servicioTransacciones;
    private ServicioReportes servicioReportes;
    
    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        this.gestorCuentas = new GestorCuentas();
        this.historial = new HistorialTransacciones();
        this.colaTransacciones = new ColaTransacciones();
        this.servicioTransacciones = new ServicioTransacciones(gestorCuentas, historial);
        this.servicioReportes = new ServicioReportes(gestorCuentas, historial);
        
        cargarDatos();
    }
    
    private void cargarDatos() {
        GestorArchivos.cargarCuentas(gestorCuentas);
        GestorArchivos.cargarTransacciones(historial);
    }
    
    private void guardarDatos() {
        GestorArchivos.guardarCuentas(gestorCuentas);
        GestorArchivos.guardarTransacciones(historial);
    }
    
    public void mostrar() {
        int opcion;
        
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 0);
        
        guardarDatos();
        Logger.registrar("Sistema cerrado");
    }
    
    private void mostrarMenu() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE GESTIÓN BANCARIA - UCC        ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║  1. Gestión de Cuentas                        ║");
        System.out.println("║  2. Realizar Transacciones                    ║");
        System.out.println("║  3. Consultar Información                     ║");
        System.out.println("║  4. Generar Reportes                          ║");
        System.out.println("║  5. Procesar Cola de Transacciones            ║");
        System.out.println("║  0. Salir                                     ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }
    
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                new InterfazCuentas(gestorCuentas, scanner).mostrar();
                break;
            case 2:
                new InterfazTransacciones(servicioTransacciones, scanner).mostrar();
                break;
            case 3:
                mostrarConsultas();
                break;
            case 4:
                mostrarReportes();
                break;
            case 5:
                procesarColaTransacciones();
                break;
            case 0:
                System.out.println("\n¡Gracias por usar el sistema!");
                break;
            default:
                System.out.println("\n⚠ Opción inválida. Intente nuevamente.");
        }
    }
    
    private void mostrarConsultas() {
        System.out.println("\n=== CONSULTAR INFORMACIÓN ===");
        System.out.print("Ingrese número de cuenta: ");
        String numero = scanner.nextLine();
        
        var cuenta = gestorCuentas.buscarCuenta(numero);
        if (cuenta != null) {
            System.out.println("\n" + cuenta);
            System.out.println("Saldo actual: $" + cuenta.getSaldo());
        } else {
            System.out.println("\n⚠ Cuenta no encontrada.");
        }
    }
    
    private void mostrarReportes() {
        System.out.println("\n=== REPORTES ===");
        System.out.println("1. Reporte General de Cuentas");
        System.out.println("2. Cuentas con Saldos Altos");
        System.out.println("3. Historial de Transacciones");
        System.out.print("Seleccione: ");
        
        int opcion = leerOpcion();
        
        switch (opcion) {
            case 1:
                System.out.println(servicioReportes.generarReporteCuentas());
                break;
            case 2:
                System.out.print("Ingrese límite mínimo: ");
                double limite = Double.parseDouble(scanner.nextLine());
                System.out.println(servicioReportes.generarReporteSaldosAltos(limite));
                break;
            case 3:
                System.out.println(servicioReportes.generarReporteTransacciones());
                break;
            default:
                System.out.println("\n⚠ Opción inválida.");
        }
    }
    
    private void procesarColaTransacciones() {
        System.out.println("\n=== PROCESAR COLA DE TRANSACCIONES ===");
        System.out.println("Transacciones pendientes: " + colaTransacciones.cantidadPendientes());
        
        while (colaTransacciones.hayPendientes()) {
            var trans = colaTransacciones.procesarSiguiente();
            System.out.println("Procesando: " + trans);
            historial.agregar(trans);
        }
        
        System.out.println("\n✓ Todas las transacciones procesadas.");
    }
}
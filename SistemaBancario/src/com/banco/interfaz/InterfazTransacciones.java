package com.banco.interfaz;

import com.banco.servicios.ServicioTransacciones;
import com.banco.excepciones.*;
import java.util.Scanner;

public class InterfazTransacciones {
    private ServicioTransacciones servicioTransacciones;
    private Scanner scanner;
    
    public InterfazTransacciones(ServicioTransacciones servicioTransacciones, Scanner scanner) {
        this.servicioTransacciones = servicioTransacciones;
        this.scanner = scanner;
    }
    
    public void mostrar() {
        System.out.println("\n=== TRANSACCIONES ===");
        System.out.println("1. Depositar");
        System.out.println("2. Retirar");
        System.out.println("3. Transferir");
        System.out.print("Seleccione: ");
        
        int opcion = leerOpcion();
        
        switch (opcion) {
            case 1: realizarDeposito(); break;
            case 2: realizarRetiro(); break;
            case 3: realizarTransferencia(); break;
            default: System.out.println("\n⚠ Opción inválida.");
        }
    }
    
    private void realizarDeposito() {
        try {
            System.out.println("\n--- DEPÓSITO ---");
            System.out.print("Número de cuenta: ");
            String cuenta = scanner.nextLine();
            
            System.out.print("Monto a depositar: $");
            double monto = Double.parseDouble(scanner.nextLine());
            
            servicioTransacciones.realizarDeposito(cuenta, monto);
            System.out.println("\n✓ Depósito realizado exitosamente.");
            
        } catch (CuentaNoEncontradaException | MontoInvalidoException e) {
            System.out.println("\n⚠ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n⚠ Monto inválido.");
        }
    }
    
    private void realizarRetiro() {
        try {
            System.out.println("\n--- RETIRO ---");
            System.out.print("Número de cuenta: ");
            String cuenta = scanner.nextLine();
            
            System.out.print("Monto a retirar: $");
            double monto = Double.parseDouble(scanner.nextLine());
            
            servicioTransacciones.realizarRetiro(cuenta, monto);
            System.out.println("\n✓ Retiro realizado exitosamente.");
            
        } catch (CuentaNoEncontradaException | MontoInvalidoException | SaldoInsuficienteException e) {
            System.out.println("\n⚠ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n⚠ Monto inválido.");
        }
    }
    
    private void realizarTransferencia() {
        try {
            System.out.println("\n--- TRANSFERENCIA ---");
            System.out.print("Cuenta origen: ");
            String origen = scanner.nextLine();
            
            System.out.print("Cuenta destino: ");
            String destino = scanner.nextLine();
            
            System.out.print("Monto a transferir: $");
            double monto = Double.parseDouble(scanner.nextLine());
            
            servicioTransacciones.realizarTransferencia(origen, destino, monto);
            System.out.println("\n✓ Transferencia realizada exitosamente.");
            
        } catch (CuentaNoEncontradaException | MontoInvalidoException | SaldoInsuficienteException e) {
            System.out.println("\n⚠ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n⚠ Monto inválido.");
        }
    }
    
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
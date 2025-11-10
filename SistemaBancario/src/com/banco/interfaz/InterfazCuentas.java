package com.banco.interfaz;

import com.banco.modelos.*;
import com.banco.estructuras.GestorCuentas;
import com.banco.utilidades.GeneradorID;
import java.util.Scanner;

public class InterfazCuentas {
    private GestorCuentas gestorCuentas;
    private Scanner scanner;
    
    public InterfazCuentas(GestorCuentas gestorCuentas, Scanner scanner) {
        this.gestorCuentas = gestorCuentas;
        this.scanner = scanner;
    }
    
    public void mostrar() {
        System.out.println("\n=== GESTIÓN DE CUENTAS ===");
        System.out.println("1. Crear nueva cuenta");
        System.out.println("2. Listar todas las cuentas");
        System.out.println("3. Buscar cuenta");
        System.out.println("4. Eliminar cuenta");
        System.out.print("Seleccione: ");
        
        int opcion = leerOpcion();
        
        switch (opcion) {
            case 1: crearCuenta(); break;
            case 2: listarCuentas(); break;
            case 3: buscarCuenta(); break;
            case 4: eliminarCuenta(); break;
            default: System.out.println("\n⚠ Opción inválida.");
        }
    }
    
    private void crearCuenta() {
        System.out.println("\n--- CREAR NUEVA CUENTA ---");
        
        System.out.print("Nombre del titular: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Apellido del titular: ");
        String apellido = scanner.nextLine();
        
        System.out.print("Documento de identidad: ");
        String documento = scanner.nextLine();
        
        System.out.print("Tipo de cuenta (AHORROS/CORRIENTE): ");
        String tipoCuenta = scanner.nextLine().toUpperCase();
        
        Cliente cliente = new Cliente(GeneradorID.generarIDCliente(), nombre, apellido, documento);
        String numeroCuenta = GeneradorID.generarNumeroCuenta();
        Cuenta cuenta = new Cuenta(numeroCuenta, cliente, tipoCuenta);
        
        if (gestorCuentas.agregarCuenta(cuenta)) {
            System.out.println("\n✓ Cuenta creada exitosamente!");
            System.out.println("Número de cuenta: " + numeroCuenta);
        } else {
            System.out.println("\n⚠ Error al crear la cuenta.");
        }
    }
    
    private void listarCuentas() {
        System.out.println("\n--- LISTA DE CUENTAS ---");
        var cuentas = gestorCuentas.listarCuentasActivas();
        
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
        } else {
            for (Cuenta c : cuentas) {
                System.out.println(c);
            }
        }
    }
    
    private void buscarCuenta() {
        System.out.print("\nIngrese número de cuenta: ");
        String numero = scanner.nextLine();
        
        Cuenta cuenta = gestorCuentas.buscarCuenta(numero);
        if (cuenta != null) {
            System.out.println("\n" + cuenta);
        } else {
            System.out.println("\n⚠ Cuenta no encontrada.");
        }
    }
    
    private void eliminarCuenta() {
        System.out.print("\nIngrese número de cuenta a eliminar: ");
        String numero = scanner.nextLine();
        
        if (gestorCuentas.eliminarCuenta(numero)) {
            System.out.println("\n✓ Cuenta eliminada exitosamente.");
        } else {
            System.out.println("\n⚠ No se pudo eliminar la cuenta.");
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
package com.banco;

import com.banco.modelos.*;
import com.banco.estructuras.*;
import com.banco.servicios.*;
import com.banco.algoritmos.*;
import com.banco.persistencia.*;
import com.banco.utilidades.*;
import com.banco.excepciones.*;
import java.util.List;

public class TestSistema {
    
    private static GestorCuentas gestorCuentas;
    private static HistorialTransacciones historial;
    private static ServicioTransacciones servicioTransacciones;
    private static ServicioReportes servicioReportes;
    private static ColaTransacciones colaTransacciones;
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║        PRUEBA AUTOMÁTICA DEL SISTEMA BANCARIO     ║");
        System.out.println("║           Universidad Cooperativa de Colombia      ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        inicializarSistema();
        
        ejecutarPrueba("1. CREAR CUENTAS", TestSistema::pruebaCrearCuentas);
        ejecutarPrueba("2. REALIZAR DEPÓSITOS", TestSistema::pruebaDepositos);
        ejecutarPrueba("3. REALIZAR RETIROS", TestSistema::pruebaRetiros);
        ejecutarPrueba("4. REALIZAR TRANSFERENCIAS", TestSistema::pruebaTransferencias);
        ejecutarPrueba("5. PROBAR VALIDACIONES (Errores Controlados)", TestSistema::pruebaValidaciones);
        ejecutarPrueba("6. ALGORITMOS DE BÚSQUEDA", TestSistema::pruebaBusqueda);
        ejecutarPrueba("7. ALGORITMOS DE ORDENAMIENTO", TestSistema::pruebaOrdenamiento);
        ejecutarPrueba("8. GENERAR REPORTES", TestSistema::pruebaReportes);
        ejecutarPrueba("9. COLA DE TRANSACCIONES", TestSistema::pruebaColaTransacciones);
        ejecutarPrueba("10. PERSISTENCIA DE DATOS", TestSistema::pruebaPersistencia);
        
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║             ✅ TODAS LAS PRUEBAS COMPLETADAS       ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
    }
    
    private static void inicializarSistema() {
        gestorCuentas = new GestorCuentas();
        historial = new HistorialTransacciones();
        colaTransacciones = new ColaTransacciones();
        servicioTransacciones = new ServicioTransacciones(gestorCuentas, historial);
        servicioReportes = new ServicioReportes(gestorCuentas, historial);
        Logger.registrar("Sistema de pruebas iniciado");
    }
    
    private static void ejecutarPrueba(String nombre, Runnable prueba) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  " + nombre);
        System.out.println("=".repeat(60));
        try {
            prueba.run();
            System.out.println("✅ Prueba completada exitosamente");
        } catch (Exception e) {
            System.out.println("❌ Error en la prueba: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // ========== PRUEBA 1: CREAR CUENTAS ==========
    private static void pruebaCrearCuentas() {
        System.out.println("\n📝 Creando cuentas de prueba...\n");
        
        // Cuenta 1: Juan Pérez
        Cliente cliente1 = new Cliente(
            GeneradorID.generarIDCliente(),
            "Juan",
            "Pérez",
            "123456789"
        );
        Cuenta cuenta1 = new Cuenta(
            GeneradorID.generarNumeroCuenta(),
            cliente1,
            Configuracion.TIPO_AHORROS
        );
        gestorCuentas.agregarCuenta(cuenta1);
        System.out.println("✓ Cuenta creada: " + cuenta1.getNumeroCuenta() + " - " + cliente1.getNombreCompleto());
        
        // Cuenta 2: María López
        Cliente cliente2 = new Cliente(
            GeneradorID.generarIDCliente(),
            "María",
            "López",
            "987654321"
        );
        Cuenta cuenta2 = new Cuenta(
            GeneradorID.generarNumeroCuenta(),
            cliente2,
            Configuracion.TIPO_CORRIENTE
        );
        gestorCuentas.agregarCuenta(cuenta2);
        System.out.println("✓ Cuenta creada: " + cuenta2.getNumeroCuenta() + " - " + cliente2.getNombreCompleto());
        
        // Cuenta 3: Carlos García
        Cliente cliente3 = new Cliente(
            GeneradorID.generarIDCliente(),
            "Carlos",
            "García",
            "456789123"
        );
        Cuenta cuenta3 = new Cuenta(
            GeneradorID.generarNumeroCuenta(),
            cliente3,
            Configuracion.TIPO_AHORROS
        );
        gestorCuentas.agregarCuenta(cuenta3);
        System.out.println("✓ Cuenta creada: " + cuenta3.getNumeroCuenta() + " - " + cliente3.getNombreCompleto());
        
        // Cuenta 4: Ana Martínez
        Cliente cliente4 = new Cliente(
            GeneradorID.generarIDCliente(),
            "Ana",
            "Martínez",
            "789123456"
        );
        Cuenta cuenta4 = new Cuenta(
            GeneradorID.generarNumeroCuenta(),
            cliente4,
            Configuracion.TIPO_CORRIENTE
        );
        gestorCuentas.agregarCuenta(cuenta4);
        System.out.println("✓ Cuenta creada: " + cuenta4.getNumeroCuenta() + " - " + cliente4.getNombreCompleto());
        
        System.out.println("\n📊 Total de cuentas creadas: " + gestorCuentas.getCantidadCuentas());
    }
    
    // ========== PRUEBA 2: DEPÓSITOS ==========
    private static void pruebaDepositos() {
        System.out.println("\n💰 Realizando depósitos...\n");
        
        List<Cuenta> cuentas = gestorCuentas.listarCuentasActivas();
        
        try {
            servicioTransacciones.realizarDeposito(cuentas.get(0).getNumeroCuenta(), 1000000);
            System.out.println("✓ Depósito de $1,000,000 en cuenta de " + cuentas.get(0).getTitular().getNombreCompleto());
            
            servicioTransacciones.realizarDeposito(cuentas.get(1).getNumeroCuenta(), 750000);
            System.out.println("✓ Depósito de $750,000 en cuenta de " + cuentas.get(1).getTitular().getNombreCompleto());
            
            servicioTransacciones.realizarDeposito(cuentas.get(2).getNumeroCuenta(), 500000);
            System.out.println("✓ Depósito de $500,000 en cuenta de " + cuentas.get(2).getTitular().getNombreCompleto());
            
            servicioTransacciones.realizarDeposito(cuentas.get(3).getNumeroCuenta(), 250000);
            System.out.println("✓ Depósito de $250,000 en cuenta de " + cuentas.get(3).getTitular().getNombreCompleto());
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        
        System.out.println("\n📊 Transacciones registradas: " + historial.getTamaño());
    }
    
    // ========== PRUEBA 3: RETIROS ==========
    private static void pruebaRetiros() {
        System.out.println("\n💸 Realizando retiros...\n");
        
        List<Cuenta> cuentas = gestorCuentas.listarCuentasActivas();
        
        try {
            servicioTransacciones.realizarRetiro(cuentas.get(0).getNumeroCuenta(), 200000);
            System.out.println("✓ Retiro de $200,000 de cuenta de " + cuentas.get(0).getTitular().getNombreCompleto());
            System.out.println("  Saldo actual: " + FormateadorDatos.formatearMoneda(cuentas.get(0).getSaldo()));
            
            servicioTransacciones.realizarRetiro(cuentas.get(1).getNumeroCuenta(), 150000);
            System.out.println("✓ Retiro de $150,000 de cuenta de " + cuentas.get(1).getTitular().getNombreCompleto());
            System.out.println("  Saldo actual: " + FormateadorDatos.formatearMoneda(cuentas.get(1).getSaldo()));
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    // ========== PRUEBA 4: TRANSFERENCIAS ==========
    private static void pruebaTransferencias() {
        System.out.println("\n🔄 Realizando transferencias...\n");
        
        List<Cuenta> cuentas = gestorCuentas.listarCuentasActivas();
        
        try {
            String cuentaOrigen = cuentas.get(0).getNumeroCuenta();
            String cuentaDestino = cuentas.get(2).getNumeroCuenta();
            
            servicioTransacciones.realizarTransferencia(cuentaOrigen, cuentaDestino, 150000);
            
            System.out.println("✓ Transferencia de $150,000");
            System.out.println("  Desde: " + cuentas.get(0).getTitular().getNombreCompleto() + 
                             " → Saldo: " + FormateadorDatos.formatearMoneda(cuentas.get(0).getSaldo()));
            System.out.println("  Hacia: " + cuentas.get(2).getTitular().getNombreCompleto() + 
                             " → Saldo: " + FormateadorDatos.formatearMoneda(cuentas.get(2).getSaldo()));
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    // ========== PRUEBA 5: VALIDACIONES ==========
    private static void pruebaValidaciones() {
        System.out.println("\n🛡️ Probando validaciones del sistema...\n");
        
        List<Cuenta> cuentas = gestorCuentas.listarCuentasActivas();
        
        // Prueba 1: Saldo insuficiente
        System.out.println("➤ Prueba: Retirar más del saldo disponible");
        try {
            servicioTransacciones.realizarRetiro(cuentas.get(3).getNumeroCuenta(), 999999999);
            System.out.println("  ❌ La validación NO funcionó");
        } catch (SaldoInsuficienteException e) {
            System.out.println("  ✓ Validación correcta: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ⚠ Error inesperado: " + e.getMessage());
        }
        
        // Prueba 2: Cuenta no encontrada
        System.out.println("\n➤ Prueba: Depositar en cuenta inexistente");
        try {
            servicioTransacciones.realizarDeposito("9999999999", 100000);
            System.out.println("  ❌ La validación NO funcionó");
        } catch (CuentaNoEncontradaException e) {
            System.out.println("  ✓ Validación correcta: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ⚠ Error inesperado: " + e.getMessage());
        }
        
        // Prueba 3: Monto inválido
        System.out.println("\n➤ Prueba: Depositar monto negativo");
        try {
            servicioTransacciones.realizarDeposito(cuentas.get(0).getNumeroCuenta(), -5000);
            System.out.println("  ❌ La validación NO funcionó");
        } catch (MontoInvalidoException e) {
            System.out.println("  ✓ Validación correcta: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ⚠ Error inesperado: " + e.getMessage());
        }
    }
    
    // ========== PRUEBA 6: BÚSQUEDA ==========
    private static void pruebaBusqueda() {
        System.out.println("\n🔍 Probando algoritmos de búsqueda...\n");
        
        List<Cuenta> cuentas = gestorCuentas.listarCuentasActivas();
        
        // Búsqueda por titular
        System.out.println("➤ Búsqueda por titular (contenga 'María'):");
        List<Cuenta> resultados = AlgoritmosBusqueda.buscarPorTitular(cuentas, "María");
        for (Cuenta c : resultados) {
            System.out.println("  ✓ Encontrada: " + c.getTitular().getNombreCompleto() + 
                             " - Cuenta: " + c.getNumeroCuenta());
        }
        
        // Búsqueda por rango de saldo
        System.out.println("\n➤ Búsqueda por rango de saldo ($400,000 - $700,000):");
        List<Cuenta> rangoSaldo = AlgoritmosBusqueda.buscarPorRangoSaldo(cuentas, 400000, 700000);
        for (Cuenta c : rangoSaldo) {
            System.out.println("  ✓ " + c.getTitular().getNombreCompleto() + 
                             " - Saldo: " + FormateadorDatos.formatearMoneda(c.getSaldo()));
        }
    }
    
    // ========== PRUEBA 7: ORDENAMIENTO ==========
    private static void pruebaOrdenamiento() {
        System.out.println("\n📊 Probando algoritmos de ordenamiento...\n");
        
        List<Cuenta> cuentas = gestorCuentas.listarCuentasActivas();
        
        // Quick Sort por saldo
        System.out.println("➤ Quick Sort - Ordenar por saldo (mayor a menor):");
        AlgoritmosOrdenamiento.quickSortPorSaldo(cuentas, 0, cuentas.size() - 1);
        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println("  " + (i+1) + ". " + cuentas.get(i).getTitular().getNombreCompleto() + 
                             " - " + FormateadorDatos.formatearMoneda(cuentas.get(i).getSaldo()));
        }
        
        // Merge Sort por número
        System.out.println("\n➤ Merge Sort - Ordenar por número de cuenta:");
        cuentas = gestorCuentas.listarCuentasActivas(); // Recargar
        AlgoritmosOrdenamiento.mergeSortPorNumero(cuentas, 0, cuentas.size() - 1);
        for (Cuenta c : cuentas) {
            System.out.println("  ✓ " + c.getNumeroCuenta() + " - " + c.getTitular().getNombreCompleto());
        }
        
        // Ordenar por titular
        System.out.println("\n➤ Ordenar alfabéticamente por titular:");
        cuentas = gestorCuentas.listarCuentasActivas(); // Recargar
        AlgoritmosOrdenamiento.ordenarPorTitular(cuentas);
        for (Cuenta c : cuentas) {
            System.out.println("  ✓ " + c.getTitular().getNombreCompleto());
        }
    }
    
    // ========== PRUEBA 8: REPORTES ==========
    private static void pruebaReportes() {
        System.out.println("\n📄 Generando reportes...\n");
        
        // Reporte general
        System.out.println("➤ REPORTE GENERAL DE CUENTAS:");
        String reporteCuentas = servicioReportes.generarReporteCuentas();
        System.out.println(reporteCuentas);
        
        // Reporte de saldos altos
        System.out.println("\n➤ REPORTE DE SALDOS ALTOS (Mayor a $500,000):");
        String reporteSaldos = servicioReportes.generarReporteSaldosAltos(500000);
        System.out.println(reporteSaldos);
        
        // Historial de transacciones
        System.out.println("\n➤ HISTORIAL DE TRANSACCIONES:");
        String reporteTransacciones = servicioReportes.generarReporteTransacciones();
        System.out.println(reporteTransacciones);
    }
    
    // ========== PRUEBA 9: COLA DE TRANSACCIONES ==========
    private static void pruebaColaTransacciones() {
        System.out.println("\n⏳ Probando cola de transacciones (FIFO)...\n");
        
        // Encolar transacciones
        List<Cuenta> cuentas = gestorCuentas.listarCuentasActivas();
        
        Transaccion t1 = new Transaccion(
            GeneradorID.generarIDTransaccion(),
            Transaccion.TipoTransaccion.DEPOSITO,
            50000,
            cuentas.get(0).getNumeroCuenta(),
            null
        );
        
        Transaccion t2 = new Transaccion(
            GeneradorID.generarIDTransaccion(),
            Transaccion.TipoTransaccion.RETIRO,
            30000,
            cuentas.get(1).getNumeroCuenta(),
            null
        );
        
        Transaccion t3 = new Transaccion(
            GeneradorID.generarIDTransaccion(),
            Transaccion.TipoTransaccion.TRANSFERENCIA,
            100000,
            cuentas.get(0).getNumeroCuenta(),
            cuentas.get(2).getNumeroCuenta()
        );
        
        colaTransacciones.encolar(t1);
        colaTransacciones.encolar(t2);
        colaTransacciones.encolar(t3);
        
        System.out.println("✓ Encoladas " + colaTransacciones.cantidadPendientes() + " transacciones");
        
        System.out.println("\n➤ Procesando transacciones en orden FIFO:");
        int contador = 1;
        while (colaTransacciones.hayPendientes()) {
            Transaccion t = colaTransacciones.procesarSiguiente();
            System.out.println("  " + contador + ". " + t.getTipo() + " - " + 
                             FormateadorDatos.formatearMoneda(t.getMonto()) + " - ID: " + t.getIdTransaccion());
            contador++;
        }
    }
    
    // ========== PRUEBA 10: PERSISTENCIA ==========
    private static void pruebaPersistencia() {
        System.out.println("\n💾 Probando persistencia de datos...\n");
        
        // Guardar
        System.out.println("➤ Guardando datos en archivos...");
        boolean cuentasGuardadas = GestorArchivos.guardarCuentas(gestorCuentas);
        boolean transaccionesGuardadas = GestorArchivos.guardarTransacciones(historial);
        
        if (cuentasGuardadas && transaccionesGuardadas) {
            System.out.println("  ✓ Datos guardados exitosamente");
            System.out.println("  ✓ Archivo: data/cuentas.dat");
            System.out.println("  ✓ Archivo: data/transacciones.dat");
        } else {
            System.out.println("  ❌ Error al guardar datos");
        }
        
        // Simular carga
        System.out.println("\n➤ Simulando carga de datos...");
        GestorCuentas nuevoGestor = new GestorCuentas();
        GestorArchivos.cargarCuentas(nuevoGestor);
        System.out.println("  ✓ Cuentas cargadas: " + nuevoGestor.getCantidadCuentas());
        
        HistorialTransacciones nuevoHistorial = new HistorialTransacciones();
        GestorArchivos.cargarTransacciones(nuevoHistorial);
        System.out.println("  ✓ Transacciones cargadas: " + nuevoHistorial.getTamaño());
    }
}
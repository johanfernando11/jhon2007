package com.banco;

import com.banco.interfaz.MenuPrincipal;
import com.banco.utilidades.Configuracion;
import com.banco.utilidades.Logger;

public class Main {
    public static void main(String[] args) {
        mostrarBienvenida();
        Logger.registrar("Sistema iniciado");
        
        MenuPrincipal menu = new MenuPrincipal();
        menu.mostrar();
    }
    
    private static void mostrarBienvenida() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║                                                      ║");
        System.out.println("║      " + Configuracion.NOMBRE_BANCO + "      ║");
        System.out.println("║                                                      ║");
        System.out.println("║         SISTEMA DE GESTIÓN BANCARIA                  ║");
        System.out.println("║              Versión " + Configuracion.VERSION + "                         ║");
        System.out.println("║                                                      ║");
        System.out.println("║      Proyecto: Estructuras de Datos                  ║");
        System.out.println("║      Ing. Jhon Haide Cano Beltrán MSc.              ║");
        System.out.println("║                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");
    }
}
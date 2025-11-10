package com.banco.algoritmos;

import com.banco.modelos.Cuenta;
import java.util.List;

public class AlgoritmosBusqueda {
    
    public static Cuenta busquedaBinaria(List<Cuenta> cuentas, String numeroCuenta) {
        int inicio = 0;
        int fin = cuentas.size() - 1;
        
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            Cuenta cuentaMedio = cuentas.get(medio);
            
            int comparacion = cuentaMedio.getNumeroCuenta().compareTo(numeroCuenta);
            
            if (comparacion == 0) {
                return cuentaMedio;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }
    
    public static List<Cuenta> buscarPorTitular(List<Cuenta> cuentas, String nombreTitular) {
        List<Cuenta> resultado = new java.util.ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getTitular().getNombreCompleto().toLowerCase()
                    .contains(nombreTitular.toLowerCase())) {
                resultado.add(cuenta);
            }
        }
        return resultado;
    }
    
    public static List<Cuenta> buscarPorRangoSaldo(List<Cuenta> cuentas, 
                                                    double minimo, double maximo) {
        List<Cuenta> resultado = new java.util.ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getSaldo() >= minimo && cuenta.getSaldo() <= maximo) {
                resultado.add(cuenta);
            }
        }
        return resultado;
    }
}
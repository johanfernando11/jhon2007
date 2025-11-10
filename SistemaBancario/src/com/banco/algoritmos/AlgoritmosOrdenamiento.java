package com.banco.algoritmos;

import com.banco.modelos.Cuenta;
import java.util.List;

public class AlgoritmosOrdenamiento {
    
    public static void quickSortPorSaldo(List<Cuenta> cuentas, int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particionPorSaldo(cuentas, inicio, fin);
            quickSortPorSaldo(cuentas, inicio, indicePivote - 1);
            quickSortPorSaldo(cuentas, indicePivote + 1, fin);
        }
    }
    
    private static int particionPorSaldo(List<Cuenta> cuentas, int inicio, int fin) {
        double pivote = cuentas.get(fin).getSaldo();
        int i = inicio - 1;
        
        for (int j = inicio; j < fin; j++) {
            if (cuentas.get(j).getSaldo() >= pivote) {
                i++;
                intercambiar(cuentas, i, j);
            }
        }
        intercambiar(cuentas, i + 1, fin);
        return i + 1;
    }
    
    public static void mergeSortPorNumero(List<Cuenta> cuentas, int inicio, int fin) {
        if (inicio < fin) {
            int medio = inicio + (fin - inicio) / 2;
            mergeSortPorNumero(cuentas, inicio, medio);
            mergeSortPorNumero(cuentas, medio + 1, fin);
            merge(cuentas, inicio, medio, fin);
        }
    }
    
    private static void merge(List<Cuenta> cuentas, int inicio, int medio, int fin) {
        List<Cuenta> temp = new java.util.ArrayList<>(cuentas);
        
        int i = inicio;
        int j = medio + 1;
        int k = inicio;
        
        while (i <= medio && j <= fin) {
            if (temp.get(i).getNumeroCuenta().compareTo(temp.get(j).getNumeroCuenta()) <= 0) {
                cuentas.set(k++, temp.get(i++));
            } else {
                cuentas.set(k++, temp.get(j++));
            }
        }
        
        while (i <= medio) {
            cuentas.set(k++, temp.get(i++));
        }
    }
    
    public static void ordenarPorTitular(List<Cuenta> cuentas) {
        cuentas.sort((c1, c2) -> c1.getTitular().getNombreCompleto()
                .compareTo(c2.getTitular().getNombreCompleto()));
    }
    
    private static void intercambiar(List<Cuenta> cuentas, int i, int j) {
        Cuenta temp = cuentas.get(i);
        cuentas.set(i, cuentas.get(j));
        cuentas.set(j, temp);
    }
}
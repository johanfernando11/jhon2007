package com.banco.estructuras;

import com.banco.modelos.Transaccion;
import java.util.ArrayList;
import java.util.List;

public class HistorialTransacciones {
    private List<Transaccion> historial;
    
    public HistorialTransacciones() {
        this.historial = new ArrayList<>();
    }
    
    public void agregar(Transaccion transaccion) {
        historial.add(transaccion);
    }
    
    public List<Transaccion> obtenerHistorial() {
        return new ArrayList<>(historial);
    }
    
    public List<Transaccion> obtenerPorCuenta(String numeroCuenta) {
        List<Transaccion> resultado = new ArrayList<>();
        for (Transaccion t : historial) {
            if (t.getCuentaOrigen().equals(numeroCuenta) || 
                (t.getCuentaDestino() != null && t.getCuentaDestino().equals(numeroCuenta))) {
                resultado.add(t);
            }
        }
        return resultado;
    }
    
    public List<Transaccion> obtenerPorTipo(Transaccion.TipoTransaccion tipo) {
        List<Transaccion> resultado = new ArrayList<>();
        for (Transaccion t : historial) {
            if (t.getTipo() == tipo) {
                resultado.add(t);
            }
        }
        return resultado;
    }
    
    public void limpiar() {
        historial.clear();
    }
    
    public int getTamaño() {
        return historial.size();
    }
}
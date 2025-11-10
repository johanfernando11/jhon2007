package com.banco.estructuras;

import com.banco.modelos.Transaccion;
import java.util.LinkedList;
import java.util.Queue;

public class ColaTransacciones {
    private Queue<Transaccion> cola;
    
    public ColaTransacciones() {
        this.cola = new LinkedList<>();
    }
    
    public void encolar(Transaccion transaccion) {
        cola.offer(transaccion);
    }
    
    public Transaccion procesarSiguiente() {
        return cola.poll();
    }
    
    public Transaccion verSiguiente() {
        return cola.peek();
    }
    
    public boolean hayPendientes() {
        return !cola.isEmpty();
    }
    
    public int cantidadPendientes() {
        return cola.size();
    }
    
    public void limpiar() {
        cola.clear();
    }
}
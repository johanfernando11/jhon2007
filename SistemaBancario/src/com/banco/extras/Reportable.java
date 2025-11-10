package com.banco.extras;

public interface Reportable {
    String generarReporte();
    void exportarReporte(String rutaArchivo);
}
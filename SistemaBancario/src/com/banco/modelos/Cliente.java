package com.banco.modelos;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String nombre;
    private String apellido;
    private String documentoIdentidad;
    private String direccion;
    private String telefono;
    private String email;
    
    public Cliente(String id, String nombre, String apellido, String documentoIdentidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documentoIdentidad = documentoIdentidad;
    }
    
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
    
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDocumentoIdentidad() { return documentoIdentidad; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public String toString() {
        return String.format("Cliente[%s] - %s - Doc: %s", 
                id, getNombreCompleto(), documentoIdentidad);
    }
}
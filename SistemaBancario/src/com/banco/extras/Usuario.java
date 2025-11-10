package com.banco.extras;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum Rol {
        CLIENTE, EMPLEADO, ADMINISTRADOR
    }
    
    private String username;
    private String password;
    private Rol rol;
    private boolean activo;
    
    public Usuario(String username, String password, Rol rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.activo = true;
    }
    
    public boolean autenticar(String password) {
        return this.password.equals(password) && activo;
    }
    
    public String getUsername() { return username; }
    public Rol getRol() { return rol; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
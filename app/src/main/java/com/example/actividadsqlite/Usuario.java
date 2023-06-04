package com.example.actividadsqlite;

public class Usuario {
    String username, password;
    boolean registrado;
    Usuario(){ }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRegistrado(boolean registrado) { this.registrado = registrado; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isRegistrado() { return registrado; }
}

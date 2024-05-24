package br.com.fiap.GestaoDeResiduos.model;

public enum UsuarioRole {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    private UsuarioRole(String role) {this.role = role;}

    public String getRole() {return role;}
}

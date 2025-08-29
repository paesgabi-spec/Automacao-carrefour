
package com.example.api.models;

public class User {
    private String nome;
    private String email;
    private String password;
    private String administrador;

    public User(String nome, String email, String password, String administrador) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.administrador = administrador;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAdministrador() {
        return administrador;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    // Builder pattern para facilitar a criação
    public static class Builder {
        private String nome;
        private String email;
        private String password;
        private String administrador = "false"; // default value

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder administrador(String administrador) {
            this.administrador = administrador;
            return this;
        }

        public Builder administrador(boolean isAdmin) {
            this.administrador = isAdmin ? "true" : "false";
            return this;
        }

        public User build() {
            return new User(nome, email, password, administrador);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", administrador='" + administrador + '\'' +
                '}';
    }
}

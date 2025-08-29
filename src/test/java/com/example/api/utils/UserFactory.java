package com.example.api.utils;

import com.example.api.models.User;
import java.util.Random;

public class UserFactory {
    
    private static final Random random = new Random();
    
    /**
     * Cria um usuário válido com dados aleatórios
     */
    public static User createValidUser() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return new User(
            "QA Auto " + timestamp,
            "qa.auto." + timestamp + "@test.com",
            "123456",
            "true"
        );
    }
    
    /**
     * Cria um usuário válido com nome específico
     */
    public static User createValidUser(String name) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return new User(
            name,
            name.toLowerCase().replace(" ", ".") + "." + timestamp + "@test.com",
            "123456",
            "true"
        );
    }
    
    /**
     * Cria um usuário administrador
     */
    public static User createAdminUser() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return new User(
            "Admin User " + timestamp,
            "admin." + timestamp + "@test.com",
            "admin123",
            "true"
        );
    }
    
    /**
     * Cria um usuário comum (não administrador)
     */
    public static User createRegularUser() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return new User(
            "Regular User " + timestamp,
            "user." + timestamp + "@test.com",
            "user123",
            "false"
        );
    }
    
    /**
     * Cria um usuário com dados inválidos para validação
     */
    public static User createInvalidUser() {
        return new User("", "", "", "");
    }
    
    /**
     * Cria um usuário com email específico
     */
    public static User createUserWithEmail(String email) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return new User(
            "Test User " + timestamp,
            email,
            "123456",
            "true"
        );
    }
    
    /**
     * Cria um usuário com senha específica
     */
    public static User createUserWithPassword(String password) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return new User(
            "Test User " + timestamp,
            "test." + timestamp + "@test.com",
            password,
            "true"
        );
    }
    
    /**
     * Cria um usuário para atualização
     */
    public static User createUserForUpdate() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return new User(
            "To Update " + timestamp,
            "update." + timestamp + "@test.com",
            "123456",
            "true"
        );
    }
    
    /**
     * Cria um usuário para exclusão
     */
    public static User createUserForDelete() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return new User(
            "To Delete " + timestamp,
            "delete." + timestamp + "@test.com",
            "123456",
            "true"
        );
    }
    
    /**
     * Gera um email único baseado no timestamp
     */
    public static String generateUniqueEmail() {
        return "user." + System.currentTimeMillis() + "@test.com";
    }
    
    /**
     * Gera um nome único baseado no timestamp
     */
    public static String generateUniqueName() {
        return "User " + System.currentTimeMillis();
    }
}

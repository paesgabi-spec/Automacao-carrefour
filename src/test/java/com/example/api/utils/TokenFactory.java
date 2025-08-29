package com.example.api.utils;

import io.restassured.response.Response;
import com.example.api.models.User;

public class TokenFactory {
    
    private static final String LOGIN_ENDPOINT = "/login";
    
    public static String generateToken(String email, String password) {
        User loginData = new User("", email, password, "");
        Response response = BaseClient.executePost(LOGIN_ENDPOINT, loginData);
        
        validateLoginResponse(response);
        
        return response.jsonPath().getString("authorization");
    }
    
    public static String generateTokenWithDefaultCredentials() {
        String email = System.getProperty("adminEmail", "admin@serverest.dev");
        String password = System.getProperty("adminPassword", "admin");
        return generateToken(email, password);
    }
    
    private static void validateLoginResponse(Response response) {
        if (response.statusCode() != 200) {
            throw new RuntimeException("Falha na autenticação. Status: " + response.statusCode());
        }
        
        String token = response.jsonPath().getString("authorization");
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token não encontrado na resposta");
        }
    }
}

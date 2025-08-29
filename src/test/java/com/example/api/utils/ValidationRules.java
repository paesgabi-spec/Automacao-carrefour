package com.example.api.utils;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.anyOf;

public class ValidationRules {
    
    public static void validateSuccessfulUserCreation(Response response) {
        response.then()
                .statusCode(anyOf(is(201), is(200)))
                .body("message", containsString("Cadastro realizado com sucesso"));
    }
    
    public static void validateUserListResponse(Response response) {
        response.then()
                .statusCode(200)
                .body("quantidade", greaterThanOrEqualTo(0));
    }
    
    public static void validateUserByIdResponse(Response response, String expectedId) {
        response.then()
                .statusCode(200)
                .body("_id", is(expectedId));
    }
    
    public static void validateUserUpdateResponse(Response response) {
        response.then()
                .statusCode(anyOf(is(200), is(201), is(204)));
    }
    
    public static void validateUserDeleteResponse(Response response) {
        response.then()
                .statusCode(anyOf(is(200), is(204)));
    }
    
    public static void validateValidationErrorResponse(Response response) {
        response.then()
                .statusCode(anyOf(is(400), is(422)));
    }
    
    public static void validateAuthenticationErrorResponse(Response response) {
        response.then()
                .statusCode(anyOf(is(401), is(403)));
    }
    
    public static void validateRateLimitResponse(Response response) {
        response.then()
                .statusCode(anyOf(is(200), is(429)));
    }
    
    public static String extractUserIdFromResponse(Response response) {
        String userId = response.jsonPath().getString("_id");
        if (userId == null || userId.isEmpty()) {
            throw new RuntimeException("ID do usuário não encontrado na resposta");
        }
        return userId;
    }
}

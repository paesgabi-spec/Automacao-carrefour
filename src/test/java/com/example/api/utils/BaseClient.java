package com.example.api.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public abstract class BaseClient {
    
    protected static Response executeGet(String endpoint) {
        return given()
                .baseUri(Config.baseUrl())
                .accept(ContentType.JSON)
                .when()
                .get(endpoint);
    }
    
    protected static Response executeGet(String endpoint, String token) {
        return given()
                .baseUri(Config.baseUrl())
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get(endpoint);
    }
    
    protected static Response executePost(String endpoint, Object body) {
        return given()
                .baseUri(Config.baseUrl())
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
    }
    
    protected static Response executePost(String endpoint, Object body, String token) {
        return given()
                .baseUri(Config.baseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(body)
                .when()
                .post(endpoint);
    }
    
    protected static Response executePut(String endpoint, Object body, String token) {
        return given()
                .baseUri(Config.baseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(body)
                .when()
                .put(endpoint);
    }
    
    protected static Response executeDelete(String endpoint, String token) {
        return given()
                .baseUri(Config.baseUrl())
                .header("Authorization", token)
                .when()
                .delete(endpoint);
    }
}

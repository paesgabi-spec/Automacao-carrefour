
package com.example.api.clients;

import com.example.api.utils.BaseClient;
import io.restassured.response.Response;

public class UserClient extends BaseClient {
    private final String token;
    private static final String USERS_ENDPOINT = "/users";

    public UserClient(String token) {
        this.token = token;
    }

    public Response list() {
        return executeGet(USERS_ENDPOINT);
    }

    public Response getById(String id) {
        return executeGet(USERS_ENDPOINT + "/" + id);
    }

    public Response create(Object body) {
        return executePost(USERS_ENDPOINT, body, token);
    }

    public Response update(String id, Object body) {
        return executePut(USERS_ENDPOINT + "/" + id, body, token);
    }

    public Response delete(String id) {
        return executeDelete(USERS_ENDPOINT + "/" + id, token);
    }
}

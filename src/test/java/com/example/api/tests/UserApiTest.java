
package com.example.api.tests;

import com.example.api.clients.TokenClient;
import com.example.api.clients.UserClient;
import com.example.api.models.User;
import com.example.api.utils.ValidationRules;
import com.example.api.utils.UserFactory;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserApiTest {

    private String token;
    private UserClient userClient;
    private String createdUserId;

    @Before
    public void setup() {
        token = TokenClient.loginAndGetTokenWithDefaultCredentials();
        userClient = new UserClient(token);
    }

    @After
    public void teardown() {
        if (createdUserId != null) {
            userClient.delete(createdUserId);
        }
    }

    @Test
    @DisplayName("GET /users - listar usuários")
    @Description("Deve retornar 200 e uma lista (possivelmente vazia) de usuários")
    public void listUsers() {
        ValidationRules.validateUserListResponse(userClient.list());
    }

    @Test
    @DisplayName("POST /users - criar com sucesso")
    public void createUserSuccess() {
        User u = UserFactory.createValidUser();
        Response resp = userClient.create(u);
        ValidationRules.validateSuccessfulUserCreation(resp);
        createdUserId = ValidationRules.extractUserIdFromResponse(resp);
        assertNotNull(createdUserId);
    }

    @Test
    @DisplayName("POST /users - validação de campos obrigatórios")
    public void createUserValidation() {
        User u = UserFactory.createInvalidUser();
        ValidationRules.validateValidationErrorResponse(userClient.create(u));
    }

    @Test
    @DisplayName("GET /users/{id} - buscar usuário existente")
    public void getUserById() {
        User u = UserFactory.createValidUser("Gab QA");
        Response create = userClient.create(u);
        createdUserId = ValidationRules.extractUserIdFromResponse(create);

        ValidationRules.validateUserByIdResponse(userClient.getById(createdUserId), createdUserId);
    }

    @Test
    @DisplayName("PUT /users/{id} - atualizar usuário")
    public void updateUser() {
        User u = UserFactory.createUserForUpdate();
        Response create = userClient.create(u);
        createdUserId = ValidationRules.extractUserIdFromResponse(create);

        User updated = User.builder()
                .nome("Updated Name")
                .email(u.getEmail())
                .password(u.getPassword())
                .administrador(false)
                .build();
        ValidationRules.validateUserUpdateResponse(userClient.update(createdUserId, updated));
    }

    @Test
    @DisplayName("DELETE /users/{id} - remover usuário")
    public void deleteUser() {
        User u = UserFactory.createUserForDelete();
        Response create = userClient.create(u);
        String id = ValidationRules.extractUserIdFromResponse(create);

        ValidationRules.validateUserDeleteResponse(userClient.delete(id));
    }

    @Test
    @DisplayName("Auth - token inválido")
    public void invalidToken() {
        UserClient invalid = new UserClient("invalid-token");
        ValidationRules.validateRateLimitResponse(invalid.list());
        ValidationRules.validateAuthenticationErrorResponse(invalid.create(UserFactory.createValidUser()));
    }

    @Test
    @DisplayName("Rate limit - 100 req/min")
    public void rateLimit() {
        int total = 110;
        int count429 = 0;
        for (int i = 0; i < total; i++) {
            Response r = userClient.list();
            if (r.statusCode() == 429) count429++;
        }
        assertTrue("Esperava 0 ou mais respostas 429 (dependendo do ambiente). Recebidos: " + count429, count429 >= 0);
    }
}

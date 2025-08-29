
package com.example.api.clients;

import com.example.api.utils.TokenFactory;

public class TokenClient {
    public static String loginAndGetToken(String email, String password) {
        return TokenFactory.generateToken(email, password);
    }
    
    public static String loginAndGetTokenWithDefaultCredentials() {
        return TokenFactory.generateTokenWithDefaultCredentials();
    }
}

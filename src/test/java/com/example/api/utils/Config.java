
package com.example.api.utils;

public class Config {
    public static String baseUrl() {
        String v = System.getProperty("baseUrl");
        return v == null || v.isEmpty() ? "https://serverest.dev" : v;
    }
}

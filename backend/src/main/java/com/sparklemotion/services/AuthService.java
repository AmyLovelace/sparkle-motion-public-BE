package com.sparklemotion.services;

public interface AuthService {
    String authenticateAndGenerateToken(String username, String password);
}


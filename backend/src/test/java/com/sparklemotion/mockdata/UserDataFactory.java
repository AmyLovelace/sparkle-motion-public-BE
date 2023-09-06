package com.sparklemotion.mockdata;

import com.sparklemotion.entities.User;

public class UserDataFactory {
    public static User createTestUser() {
        String username = "testUser";
        String password = "testPassword";
        return new User(username, password);
    }

    public static User createNonExistingUser() {
        String username = "nonExistingUser";
        return new User(username, "randomPassword");
    }
}


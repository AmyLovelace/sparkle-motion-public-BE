package com.sparklemotion.mockdata;

import com.sparklemotion.entities.User;

public class UserServiceMockData {

    public static final String EXISTING_USERNAME = "existingUsername";
    public static final String NEW_USERNAME = "newUsername";
    public static final String PASSWORD = "password";

    public static User createMockUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}

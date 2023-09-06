package com.sparklemotion.mockdata;

import com.sparklemotion.entities.User;

        import java.security.NoSuchAlgorithmException;
        import java.util.HashMap;
        import java.util.Map;

public class UserControllerTestMockdata {

    public static final String TEST_USERNAME = "testUser";
    public static final String TEST_PASSWORD = "testPassword";

    public static Map<String, String> createNewUserRequest(String username, String password) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);
        return requestBody;
    }

    public static User createNewUser(String username, String password) {
        return new User(username, password);
    }

    public static User createExistingUser(String username, String password) throws NoSuchAlgorithmException {
        User existingUser = new User(username, password);
        existingUser.setId(1L);
        return existingUser;
    }
}

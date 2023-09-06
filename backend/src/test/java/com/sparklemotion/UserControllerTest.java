package com.sparklemotion;

import com.sparklemotion.controllers.UserController;
import com.sparklemotion.entities.User;
import com.sparklemotion.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("newUsername");
        user.setPassword("password");

        when(userService.createUserIfNotExists(user)).thenReturn(true);

        Boolean result = userController.create(user);

        assertTrue(result);
        verify(userService, times(1)).createUserIfNotExists(user);
    }
}

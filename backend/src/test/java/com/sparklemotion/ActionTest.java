package com.sparklemotion;

import com.sparklemotion.entities.Action;
import com.sparklemotion.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNull;

public class ActionTest {

    @Test
    public void testActionConstructorAndGetters() {
        Long id = 1L;
        String command = "Test Command";
        String callback = "Test Callback";

        Action action = new Action(id, null, command, callback);

        assertEquals(id, action.getId());
        assertNull(null, action.getUser());
        assertEquals(command, action.getCommand());
        assertEquals(callback, action.getCallback());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        User user = new User("ami","123");
        String command = "Test Command";
        String callback = "Test Callback";

        Action action = new Action(id, user, command, callback);

        assertEquals(id, action.getId());
        assertEquals(user, action.getUser());
        assertEquals(command, action.getCommand());
        assertEquals(callback, action.getCallback());
    }

    @Test
    public void testSetterMethods() {
        Long id = 1L;
        User user = new User("amisauria","123");
        String command = "Test Command";
        String callback = "Test Callback";

        Action action = new Action();

        action.setId(id);
        action.setUser(user);
        action.setCommand(command);
        action.setCallback(callback);

        assertEquals(id, action.getId());
        assertEquals(user, action.getUser());
        assertEquals(command, action.getCommand());
        assertEquals(callback, action.getCallback());
    }


}

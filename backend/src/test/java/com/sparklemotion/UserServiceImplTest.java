package com.sparklemotion;

import com.sparklemotion.config.Error;
import com.sparklemotion.config.exceptions.ValidationException;
import com.sparklemotion.entities.User;
import com.sparklemotion.mockdata.UserServiceMockData;
import com.sparklemotion.repositories.UserRepository;
import com.sparklemotion.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = UserServiceMockData.createMockUser(UserServiceMockData.EXISTING_USERNAME, UserServiceMockData.PASSWORD);
        boolean result = userService.save(user);
        assertTrue(result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testExistsByUsernameTrue() {
        when(userRepository.existsByUsername(UserServiceMockData.EXISTING_USERNAME)).thenReturn(true);
        assertTrue(userService.existsByUsername(UserServiceMockData.EXISTING_USERNAME));
        verify(userRepository, times(1)).existsByUsername(UserServiceMockData.EXISTING_USERNAME);
    }

    @Test
    public void testExistsByUsernameFalse() {
        when(userRepository.existsByUsername(UserServiceMockData.NEW_USERNAME)).thenReturn(false);
        assertFalse(userService.existsByUsername(UserServiceMockData.NEW_USERNAME));
        verify(userRepository, times(1)).existsByUsername(UserServiceMockData.NEW_USERNAME);
    }

    @Test
    public void testCreateUserIfNotExistsNewUser() {
        User user = UserServiceMockData.createMockUser(UserServiceMockData.NEW_USERNAME, UserServiceMockData.PASSWORD);
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        boolean result = userService.createUserIfNotExists(user);

        assertTrue(result);
        verify(userRepository, times(1)).existsByUsername(user.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testCreateUserIfNotExistsExistingUser() {
        User user = UserServiceMockData.createMockUser(UserServiceMockData.EXISTING_USERNAME, UserServiceMockData.PASSWORD);
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);

        ValidationException exception = assertThrows(ValidationException.class,
                () -> userService.createUserIfNotExists(user));
        assertEquals(Error.USER_EXISTS.getMessage(), exception.getMessage());

        verify(userRepository, times(1)).existsByUsername(user.getUsername());
        verify(userRepository, never()).save(user);
    }
}

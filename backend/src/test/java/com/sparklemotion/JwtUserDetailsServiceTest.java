package com.sparklemotion;

import com.sparklemotion.mockdata.UserDataFactory;
import com.sparklemotion.entities.User;
import com.sparklemotion.repositories.UserRepository;
import com.sparklemotion.services.impl.JwtUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class JwtUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserFound() {

        User testUser = UserDataFactory.createTestUser();

        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(testUser.getUsername());

        assertNotNull(userDetails);
        assertEquals(testUser.getUsername(), userDetails.getUsername());
        assertEquals(testUser.getPassword(), userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {

        User nonExistingUser = UserDataFactory.createNonExistingUser();

        when(userRepository.findByUsername(nonExistingUser.getUsername())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername(nonExistingUser.getUsername()));
    }
}

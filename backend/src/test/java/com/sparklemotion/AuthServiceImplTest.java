package com.sparklemotion;

import com.sparklemotion.config.JwtToken;
import com.sparklemotion.config.exceptions.AuthException;
import com.sparklemotion.config.exceptions.InvalidCredentialsException;
import com.sparklemotion.mockdata.UserServiceMockData;
import com.sparklemotion.services.AuthService;
import com.sparklemotion.mockdata.UserDataFactory; // Asegúrate de importar correctamente la clase UserDataFactory
import com.sparklemotion.services.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtToken jwtToken;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testAuthenticateAndGenerateToken() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(UserServiceMockData.NEW_USERNAME)).thenReturn(userDetails);
        when(jwtToken.generateToken(userDetails)).thenReturn("testToken");

        assertDoesNotThrow(() -> authService.authenticateAndGenerateToken(UserServiceMockData.NEW_USERNAME, UserServiceMockData.PASSWORD));

        verify(authenticationManager, times(1))
                .authenticate(new UsernamePasswordAuthenticationToken(UserServiceMockData.NEW_USERNAME, UserServiceMockData.PASSWORD));
        verify(userDetailsService, times(1)).loadUserByUsername( UserServiceMockData.NEW_USERNAME);
        verify(jwtToken, times(1)).generateToken(userDetails);
    }

    @Test
    public void testAuthenticateAndGenerateTokenInvalidCredentials() {


        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(UserServiceMockData.NEW_USERNAME, UserServiceMockData.PASSWORD)))
                .thenThrow(InvalidCredentialsException.class);

        assertThrows(InvalidCredentialsException.class, () -> authService.authenticateAndGenerateToken(UserServiceMockData.NEW_USERNAME, UserServiceMockData.PASSWORD));

        verify(authenticationManager, times(1))
                .authenticate(new UsernamePasswordAuthenticationToken(UserServiceMockData.NEW_USERNAME, UserServiceMockData.PASSWORD));
        verify(userDetailsService, never()).loadUserByUsername(UserServiceMockData.NEW_USERNAME);
        verify(jwtToken, never()).generateToken(any());
    }
}

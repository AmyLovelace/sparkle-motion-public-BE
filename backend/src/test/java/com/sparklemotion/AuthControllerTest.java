package com.sparklemotion;

import com.sparklemotion.mockdata.JwtUserDetailsServiceMockData;
import com.sparklemotion.config.JwtToken;
import com.sparklemotion.controllers.AuthController;
import com.sparklemotion.entities.JwtRequest;
import com.sparklemotion.entities.JwtResponse;
import com.sparklemotion.services.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class AuthControllerTest {

    @Mock
    private AuthServiceImpl mockAuthService;

    @InjectMocks
    private AuthController authController;

    private JwtRequest validAuthenticationRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        validAuthenticationRequest = new JwtRequest();
        validAuthenticationRequest.setUsername("testUser");
        validAuthenticationRequest.setPassword("testPassword");
    }

    @Test
    @DisplayName("createAuthenticationToken() - Success")
    public void givenValidAuthenticationRequest_whenCreateAuthenticationToken_thenTokenReturned() throws Exception {

        String token = "testToken";
        when(mockAuthService.authenticateAndGenerateToken("testUser", "testPassword")).thenReturn(token);

        ResponseEntity<?> responseEntity = authController.createAuthenticationToken(validAuthenticationRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof JwtResponse);
        JwtResponse jwtResponse = (JwtResponse) responseEntity.getBody();
        assertEquals(token, jwtResponse.getToken());

        verify(mockAuthService, times(1)).authenticateAndGenerateToken("testUser", "testPassword");
    }

}

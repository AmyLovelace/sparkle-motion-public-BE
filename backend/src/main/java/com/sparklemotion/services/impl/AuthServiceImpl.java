package com.sparklemotion.services.impl;

import com.sparklemotion.config.Error;
import com.sparklemotion.config.JwtToken;
import com.sparklemotion.config.exceptions.AuthException;
import com.sparklemotion.config.exceptions.InvalidCredentialsException;
import com.sparklemotion.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    private final UserDetailsService userDetailsService;

    public String authenticateAndGenerateToken(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return jwtToken.generateToken(userDetails);
        } catch (DisabledException ex) {
            log.error(Error.USER_DISABLED.getMessage(), ex);
            throw new AuthException();
        } catch (InvalidCredentialsException ex) {
            log.error(Error.INVALID_CREDENTIALS.getMessage(), ex);
            throw new InvalidCredentialsException(Error.INVALID_CREDENTIALS.getMessage());
        }
    }
}

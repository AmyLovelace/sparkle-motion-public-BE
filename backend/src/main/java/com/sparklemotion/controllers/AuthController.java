package com.sparklemotion.controllers;

import com.sparklemotion.entities.JwtRequest;
import com.sparklemotion.entities.JwtResponse;
import com.sparklemotion.services.impl.AuthServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

    @RestController
    @CrossOrigin
    @RequestMapping("/api/v1/authenticate")
    @RequiredArgsConstructor
    @Slf4j
    public class AuthController {

        private final AuthServiceImpl authService;

        @PostMapping
        public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
            final String token = authService.authenticateAndGenerateToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            return ResponseEntity.ok(new JwtResponse(token));
        }
    }







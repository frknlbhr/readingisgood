package com.filbahar.readingisgood.controller;

import com.filbahar.readingisgood.dto.JwtRequest;
import com.filbahar.readingisgood.dto.JwtResponse;
import com.filbahar.readingisgood.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@RestController
@RequestMapping(value = "/auth")
public class JwtAuthController {

    private final AuthService authService;

    @Autowired
    public JwtAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/getToken")
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody JwtRequest authRequest) {
        JwtResponse authResponse = authService.createAuthToken(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}

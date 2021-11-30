package com.filbahar.readingisgood.service;

import com.filbahar.readingisgood.dto.JwtRequest;
import com.filbahar.readingisgood.dto.JwtResponse;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public interface AuthService {

    JwtResponse createAuthToken(JwtRequest authRequest);

}

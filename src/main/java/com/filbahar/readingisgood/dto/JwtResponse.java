package com.filbahar.readingisgood.dto;

import java.io.Serializable;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getJwttoken() {
        return jwttoken;
    }
}

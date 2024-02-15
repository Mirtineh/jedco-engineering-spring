package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.LoginRequest;
import com.jedco.jedcoengineeringspring.rest.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse login(LoginRequest request);

    JwtAuthenticationResponse refreshToken();
}

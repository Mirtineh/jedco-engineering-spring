package com.jedco.jedcoengineeringspring.controllers;

import com.jedco.jedcoengineeringspring.rest.request.LoginRequest;
import com.jedco.jedcoengineeringspring.rest.response.JwtAuthenticationResponse;
import com.jedco.jedcoengineeringspring.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Login Endpoint", description = "This use case to login to the system and to get a JWT session token.\n\n" + "The JWT token should be used in the header using the 'Bearer jwt-token' format. It can be regenerated without a second login using the auth/refresh endpoint while the session is active.")
    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh() {
        return ResponseEntity.ok(authenticationService.refreshToken());
    }
}

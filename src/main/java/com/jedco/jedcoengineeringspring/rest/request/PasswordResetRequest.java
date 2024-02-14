package com.jedco.jedcoengineeringspring.rest.request;

public record PasswordResetRequest(
        Long userId,
        String password,
        String confirmPassword
) {
}

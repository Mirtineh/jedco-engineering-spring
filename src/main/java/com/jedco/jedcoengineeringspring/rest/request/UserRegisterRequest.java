package com.jedco.jedcoengineeringspring.rest.request;

public record UserRegisterRequest(
        Long roleId,
        String firstName,
        String lastName,
        String phoneNo,
        String email,
        String username,
        String password
) {
}

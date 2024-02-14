package com.jedco.jedcoengineeringspring.rest.request;

public record UpdateUserProfileRequest(
        Long userId,
        Long userRoleId,
        String firstName,
        String lastName,
        String email,
        String phone,
        String password
) {
}

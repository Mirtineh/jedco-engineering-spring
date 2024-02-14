package com.jedco.jedcoengineeringspring.rest.request;

public record UserRoleUpdateRequest(
        Long id,
        String name,
        String description
) {
}

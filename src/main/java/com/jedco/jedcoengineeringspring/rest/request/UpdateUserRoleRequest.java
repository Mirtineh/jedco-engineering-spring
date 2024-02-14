package com.jedco.jedcoengineeringspring.rest.request;

public record UpdateUserRoleRequest(
        Long userId,
        Long userRoleId
) {
}

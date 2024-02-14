package com.jedco.jedcoengineeringspring.rest.response;

import java.util.Date;

public record UserRoleResponse(
        Long id,
        String name,
        String status,
        String description,
        Date createdOn
) {
}

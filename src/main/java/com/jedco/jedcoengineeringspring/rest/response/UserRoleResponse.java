package com.jedco.jedcoengineeringspring.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record UserRoleResponse(
        Long id,
        String name,
        String status,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.NUMBER)
        Date createdOn
) {
}

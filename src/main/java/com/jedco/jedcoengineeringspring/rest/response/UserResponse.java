package com.jedco.jedcoengineeringspring.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record UserResponse(
        Long id,
        Long userStatusId,
        String userStatus,
        Long userRoleId,
        String userRole,
        String firstName,
        String lastName,
        String phoneNo,
        String email,
        String username,
        String registeredBy,
        @JsonFormat(shape = JsonFormat.Shape.NUMBER)
        Date registeredDate,
        String activationNo
) {
}

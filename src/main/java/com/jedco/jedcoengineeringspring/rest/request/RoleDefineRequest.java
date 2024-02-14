package com.jedco.jedcoengineeringspring.rest.request;

import java.util.List;

public record RoleDefineRequest(
        String userRoleName,
        String userRoleDescription,
        List<Long> userActions
) {
}

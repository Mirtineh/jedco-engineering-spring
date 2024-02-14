package com.jedco.jedcoengineeringspring.rest.response;

import java.util.List;

public record RoleDefinitionResponse(
        String roleId,
        String roleName,
        List<UserActionResponse> userActionList
) {
}

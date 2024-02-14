package com.jedco.jedcoengineeringspring.rest.response;

import java.util.List;

public record UserActionsListByGroupResponse(
        Long groupId,
        String groupName,
        String groupDescription,
        List<ActionResponse> actionsDtos
) {
}

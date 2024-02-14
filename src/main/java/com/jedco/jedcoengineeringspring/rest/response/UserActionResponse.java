package com.jedco.jedcoengineeringspring.rest.response;

public record UserActionResponse(
        String id,
        String action,
        Long groupId,
        String groupName
) {
}

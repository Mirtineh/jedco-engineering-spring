package com.jedco.jedcoengineeringspring.rest.response;

public record ActionResponse(
        String userActionId,
        String action,
        Boolean actionStatus
) {
}

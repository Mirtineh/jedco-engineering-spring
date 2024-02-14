package com.jedco.jedcoengineeringspring.rest.request;

import java.util.List;

public record AddOrRemoveActionRequest(
        Long userRoleId,
        List<Long> userActions
) {
}

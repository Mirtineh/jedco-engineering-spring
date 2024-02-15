package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.response.UserActionsListByGroupResponse;

import java.util.List;

public interface UserActionService {
    List<UserActionsListByGroupResponse> userActionList();
}

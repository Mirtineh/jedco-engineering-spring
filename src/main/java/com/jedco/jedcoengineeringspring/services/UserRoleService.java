package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.UserRoleUpdateRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.UserRoleResponse;

import java.util.List;

public interface UserRoleService {
    ResponseDto userRoleUpdate(UserRoleUpdateRequest userRoleUpdateDto);

    List<UserRoleResponse> userRoleList();
}

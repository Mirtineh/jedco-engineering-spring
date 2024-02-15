package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.AddOrRemoveActionRequest;
import com.jedco.jedcoengineeringspring.rest.request.RoleDefineRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.RoleDefinitionResponse;

import java.util.List;

public interface RoleDefinitionService {
    ResponseDto defineRole(RoleDefineRequest roleDefineDto);

    ResponseDto updateDefinedActions(AddOrRemoveActionRequest addOrRemoveActionDto);

    ResponseDto deleteUserRole(Long userRoleId);

    List<RoleDefinitionResponse> definedRolesList(Long userRoleId);

    ResponseDto activateUserRole(Long userRoleId);

}

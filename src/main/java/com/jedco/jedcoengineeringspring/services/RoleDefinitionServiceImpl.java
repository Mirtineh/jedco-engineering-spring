package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.AddOrRemoveActionRequest;
import com.jedco.jedcoengineeringspring.rest.request.RoleDefineRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.RoleDefinitionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleDefinitionServiceImpl implements RoleDefinitionService {
    @Override
    public ResponseDto defineRole(RoleDefineRequest roleDefineDto) {
        return null;
    }

    @Override
    public ResponseDto updateDefinedActions(AddOrRemoveActionRequest addOrRemoveActionDto) {
        return null;
    }

    @Override
    public ResponseDto deleteUserRole(Long userRoleId) {
        return null;
    }

    @Override
    public List<RoleDefinitionResponse> definedRolesList(Long userRoleId) {
        return null;
    }

    @Override
    public ResponseDto activateUserRole(Long userRoleId) {
        return null;
    }
}

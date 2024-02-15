package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.UserRoleUpdateRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.UserRoleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    @Override
    public ResponseDto userRoleUpdate(UserRoleUpdateRequest userRoleUpdateDto) {
        return null;
    }

    @Override
    public List<UserRoleResponse> userRoleList() {
        return null;
    }
}

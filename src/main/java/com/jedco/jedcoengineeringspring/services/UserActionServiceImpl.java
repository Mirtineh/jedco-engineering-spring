package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.response.UserActionsListByGroupResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserActionServiceImpl implements UserActionService {
    @Override
    public List<UserActionsListByGroupResponse> userActionList() {
        return null;
    }
}
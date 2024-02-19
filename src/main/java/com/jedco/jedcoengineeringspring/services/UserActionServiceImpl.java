package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.mappers.UserMapper;
import com.jedco.jedcoengineeringspring.models.ActionGroup;
import com.jedco.jedcoengineeringspring.repositories.ActionGroupRepository;
import com.jedco.jedcoengineeringspring.rest.response.UserActionsListByGroupResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserActionServiceImpl implements UserActionService {
    private final ActionGroupRepository actionGroupRepository;
    private final UserMapper userMapper;
    @Override
    public List<UserActionsListByGroupResponse> userActionList() {
        //TODO check the side effect
        List<ActionGroup> actionGroups = actionGroupRepository.findAll();
        return actionGroups.stream().map(userMapper::toUserAction).toList();
    }
}

package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.mappers.UserMapper;
import com.jedco.jedcoengineeringspring.models.*;
import com.jedco.jedcoengineeringspring.repositories.*;
import com.jedco.jedcoengineeringspring.rest.request.AddOrRemoveActionRequest;
import com.jedco.jedcoengineeringspring.rest.request.RoleDefineRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.RoleDefinitionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleDefinitionServiceImpl implements RoleDefinitionService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final UserActionRepository userActionRepository;
    private final RoleDefinitionRepository roleDefinitionRepository;

    private final UserMapper userMapper;

    @Override
    public ResponseDto defineRole(RoleDefineRequest roleDefineDto) {
        try {
            Optional<UserRole> optionalUserRole = userRoleRepository.findByRoleName(roleDefineDto.userRoleName());
            Status activeStatus = statusRepository.findById(1L).get();
            if (optionalUserRole.isEmpty()) {
                UserRole userRole = new UserRole();
                userRole.setRoleName(roleDefineDto.userRoleName());
                userRole.setRoleDescription(roleDefineDto.userRoleDescription());
                userRole.setStatus(activeStatus);
                userRole.setRegisteredDate(new Date());
                userRoleRepository.save(userRole);

                List<Long> userActionids = roleDefineDto.userActions();
                userActionids.forEach((actionId) -> {
                    UserAction userAction = userActionRepository.findById(actionId).get();
                    RoleDefinition roleDefinition = new RoleDefinition();
                    roleDefinition.setUserRole(userRole);
                    roleDefinition.setUserAction(userAction);
                    roleDefinition.setStatus(activeStatus);
                    roleDefinitionRepository.save(roleDefinition);
                });
                return new ResponseDto(true, "User role defined successfully");
            } else {
                return new ResponseDto(false, "Role Name already taken");
            }
        } catch (Exception e) {
            log.error("Role Definition Request Failed ..." + e.getMessage());
            return new ResponseDto(false, "Operation Failed!");
        }

    }

    @Override
    public ResponseDto updateDefinedActions(AddOrRemoveActionRequest addOrRemoveActionDto) {
        try {
            Optional<UserRole> optionalUserRole = userRoleRepository.findById(addOrRemoveActionDto.userRoleId());
            if (optionalUserRole.isEmpty()) {
                return new ResponseDto(false, "User Role not Found!");
            }
            var userRole = optionalUserRole.get();
            Long activeStatus = 1L;
            List<RoleDefinition> roleDefinitions = roleDefinitionRepository.findByUserRoleIdAndStatusId(userRole.getId(), activeStatus);
            List<Long> userActionIds = addOrRemoveActionDto.userActions();

            log.info("user ---------------------------- role  " + addOrRemoveActionDto.userRoleId());
            userActionIds.forEach(id -> {
                System.out.println("user ---------------------------- actions  " + id);
            });

            roleDefinitions.forEach((definition) -> {
                if (!userActionIds.contains(definition.getId())) {
                    roleDefinitionRepository.deleteById(definition.getId());
                }
            });
            userActionIds.forEach((userActionId) -> {
                Optional<RoleDefinition> optionalRoleDefinition = roleDefinitionRepository.findOneByUserActionIdAndUserRoleId(userActionId, userRole.getId());

                if (optionalRoleDefinition.isEmpty()) {
                    UserAction action = userActionRepository.findById(userActionId).get();
                    RoleDefinition roleDefinition = new RoleDefinition();
                    roleDefinition.setUserRole(userRole);
                    roleDefinition.setUserAction(action);
                    roleDefinition.setStatus(statusRepository.findById(1L).get());
                    roleDefinitionRepository.save(roleDefinition);
                }
            });
            return new ResponseDto(true, " Role updated Successfully");


        } catch (Exception e) {
            log.error("Update defined actions failed ...." + e.getMessage());
            return new ResponseDto(false, "Operation Failed!");
        }
    }

    @Override
    public ResponseDto deleteUserRole(Long userRoleId) {
        try {
            Optional<UserRole> optionalUserRole = userRoleRepository.findById(userRoleId);
            if (optionalUserRole.isEmpty()) {
                return new ResponseDto(false, "User Role not Found!");
            }
            var userRole = optionalUserRole.get();
            Long deletedStatusId = 3L;
            Long activeStatusId = 1L;
            List<User> users = userRepository.findByUserRoleIdAndStatusIdNot(userRoleId, deletedStatusId);
            if (!users.isEmpty()) {
                return new ResponseDto(false, "There are users registered with this role");
            } else {
                Status deletedStatus = statusRepository.findById(deletedStatusId).get();
                userRole.setStatus(deletedStatus);
                userRoleRepository.save(userRole);

                List<RoleDefinition> roleDefinitions = roleDefinitionRepository.findByUserRoleIdAndStatusId(userRoleId, activeStatusId);
                roleDefinitions.forEach((roleDefinition) -> {
                    roleDefinitionRepository.deleteById(roleDefinition.getId());
                });
                return new ResponseDto(true, "Deleted Successfully!");
            }

        } catch (Exception e) {
            log.error("Delete User Role failed ...." + e.getMessage());
            return new ResponseDto(false, "Operation Failed!");
        }
    }

    @Override
    public List<RoleDefinitionResponse> definedRolesList(Long userRoleId) {
        Optional<UserRole> optionalUserRole = userRoleRepository.findById(userRoleId);
        if (optionalUserRole.isEmpty()) {
            return new ArrayList<>();
        }
        UserRole userRole = optionalUserRole.get();
        List<RoleDefinition> roleDefinitions = roleDefinitionRepository.findAllByUserRoleAndStatus_Id(userRole, 1L);
        return Collections.singletonList(userMapper.roleDefinitionResponse(roleDefinitions, userRole));
    }

    @Override
    public ResponseDto activateUserRole(Long userRoleId) {
        Optional<UserRole> optionalUserRole = userRoleRepository.findById(userRoleId);
        if (optionalUserRole.isEmpty()) {
            return new ResponseDto(false, "UserRole Not Found!");
        }
        var userRole = optionalUserRole.get();
        Long activeStatusId = 1L;
        Status activeStatus = statusRepository.findById(activeStatusId).get();
        userRole.setStatus(activeStatus);
        userRoleRepository.save(userRole);
        return new ResponseDto(true, "Activated Successfully!");
    }
}

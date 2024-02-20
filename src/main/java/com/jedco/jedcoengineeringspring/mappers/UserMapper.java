package com.jedco.jedcoengineeringspring.mappers;

import com.jedco.jedcoengineeringspring.models.*;
import com.jedco.jedcoengineeringspring.rest.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "roleId", source = "userRole.id")
    @Mapping(target = "roleName", source = "userRole.roleName")
    @Mapping(target = "userActionList", source = "roleDefinitions", qualifiedByName = "mapToUserAction")
    RoleDefinitionResponse roleDefinitionResponse(List<RoleDefinition> roleDefinitions, UserRole userRole);

    @Named("mapToUserAction")
    default List<UserActionResponse> toUserActionListDtoList(List<RoleDefinition> roleDefinitions) {
        return roleDefinitions.stream()
                .map(this::toUserActionListDto)
                .collect(Collectors.toList());
    }
    @Mapping(target = "id", source = "userAction.id")
    @Mapping(target = "action", source = "userAction.actionName")
    @Mapping(target = "groupId", source = "userAction.actionGroup.id")
    @Mapping(target = "groupName", source = "userAction.actionGroup.groupName")
    UserActionResponse toUserActionListDto(RoleDefinition roleDefinition);

    @Mapping(target = "groupName", source = "groupName")
    @Mapping(target = "groupId", source = "id")
    @Mapping(target = "groupDescription", source = "groupDescription")
    @Mapping(target = "actionsDtos", source = "userActions")
    UserActionsListByGroupResponse toUserAction(ActionGroup actionGroup);

    @Mapping(target = "userActionId", source = "id")
    @Mapping(target = "action", source = "actionName")
    @Mapping(target = "actionStatus",source = "actionStatus", qualifiedByName = "mapToActionStatus")
    ActionResponse toActionResponse(UserAction userAction);

    @Named("mapToActionStatus")
    default boolean mapToActionStatus(long actionStatus){
        return actionStatus == 1;
    }

    @Mapping(target = "name", source = "roleName")
    @Mapping(target = "description", source = "roleDescription")
    @Mapping(target = "createdOn", source = "registeredDate")
    @Mapping(target = "status", source = "status.name")
    UserRoleResponse toUserRoleResponse(UserRole userRole);

    @Mapping(target = "userStatusId", source = "status.id")
    @Mapping(target = "userStatus", source = "status.name")
    @Mapping(target = "userRoleId", source = "userRole.id")
    @Mapping(target = "registeredBy", expression = "java(user.getRegisteredBy().getFirstName() + ' ' + user.getRegisteredBy().getLastName())")
    @Mapping(target = "userRole", source = "userRole.roleName")
    UserResponse toUserResponse(User user);
}

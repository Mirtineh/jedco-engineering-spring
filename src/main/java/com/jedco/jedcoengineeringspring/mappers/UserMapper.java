package com.jedco.jedcoengineeringspring.mappers;

import com.jedco.jedcoengineeringspring.models.RoleDefinition;
import com.jedco.jedcoengineeringspring.models.UserAction;
import com.jedco.jedcoengineeringspring.models.UserRole;
import com.jedco.jedcoengineeringspring.rest.response.RoleDefinitionResponse;
import com.jedco.jedcoengineeringspring.rest.response.UserActionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "userActionList", source = "roleDefinition.userAction", qualifiedByName = "mapUserActionList")
    @Mapping(target = "roleName", source = "userRole.roleName")
    @Mapping(target = "roleId", source = "userRole.id")
    RoleDefinitionResponse toRoleDefinitionResponse(RoleDefinition roleDefinition, UserRole userRole);

    @Mapping(target = "action", source = "actionName")
    @Mapping(target = "groupName", source = "actionGroup.name")
    @Mapping(target = "groupId", source = "actionGroup.id")
    UserActionResponse toUserActionResponse(UserAction userAction);

    @Named("mapUserActionList")
    default List<UserActionResponse> mapUserActionList(UserAction userAction){
        return Collections.singletonList(toUserActionResponse(userAction));
    }
}

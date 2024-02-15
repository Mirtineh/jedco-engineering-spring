package com.jedco.jedcoengineeringspring.controllers;

import com.jedco.jedcoengineeringspring.rest.request.AddOrRemoveActionRequest;
import com.jedco.jedcoengineeringspring.rest.request.RoleDefineRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.RoleDefinitionResponse;
import com.jedco.jedcoengineeringspring.services.RoleDefinitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roleDefinition")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "UserManagement")
public class RoleDefinitionController {
    private final RoleDefinitionService roleDefinitionService;

    @PostMapping("/defineRole")
    @Operation(summary = "create role and assign authority", description = "creates userRole and define(assign) userActions(authorities) for that role")
    @PreAuthorize("hasAnyAuthority('REGISTER_ROLE')")
    public ResponseDto defineRole(@RequestBody RoleDefineRequest entity) {
        return this.roleDefinitionService.defineRole(entity);
    }

    @PutMapping("/updateDefinedActions")
    @Operation(summary = "", description = "add or remove authorities(assigned userActions) for a single userRole")
    @PreAuthorize("hasAnyAuthority('UPDATE_ROLE')")
    public ResponseDto updateDefinedActions(@RequestBody AddOrRemoveActionRequest entity) {
        return this.roleDefinitionService.updateDefinedActions(entity);
    }

    @DeleteMapping("/deleteUserRole")
    @Operation(summary = "", description = "change the status of userRole in to deleted")
    @PreAuthorize("hasAnyAuthority('DELETE_ROLE')")
    public ResponseDto deleteUserRole(@RequestParam("UserRoleID") Long id) {
        return this.roleDefinitionService.deleteUserRole(id);
    }

    @GetMapping("/definedRolesList")
    @Operation(summary = "", description = "list of all authorities(assigned actions) of a userRole")
    @PreAuthorize("hasAnyAuthority('LIST_ROLES')")
    public List<RoleDefinitionResponse> definedRolesList(@RequestParam("userRoleId") Long userRoleId) {
        return this.roleDefinitionService.definedRolesList(userRoleId);
    }

    @PostMapping("/activateRole")
    @Operation(summary = "", description = "Activate Deleted user role")
    @PreAuthorize("hasAnyAuthority('ACTIVATE_ROLE')")
    public ResponseDto activateUserRole(@RequestParam("UserRoleID") Long id) {
        return this.roleDefinitionService.activateUserRole(id);
    }
}

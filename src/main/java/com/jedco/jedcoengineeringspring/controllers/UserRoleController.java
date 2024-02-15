package com.jedco.jedcoengineeringspring.controllers;

import com.jedco.jedcoengineeringspring.rest.request.UserRoleUpdateRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.UserRoleResponse;
import com.jedco.jedcoengineeringspring.services.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/UserRole")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "UserManagement")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @PutMapping("/updateUserRole")
    @Operation(summary = "updates userRole info", description = "requires UserRoleUpdateDto from user, userRole update is available by it's roleId")
    @PreAuthorize("hasAnyAuthority('UPDATE_ROLE')")
    public ResponseDto updateUserRole(@RequestBody UserRoleUpdateRequest entity) {
        return this.userRoleService.userRoleUpdate(entity);
    }

    @GetMapping("/userRoleList")
    @Operation(summary = "", description = "list of all active(not deleted or suspended) userRoles")
    @PreAuthorize("hasAnyAuthority('LIST_ROLES')")
    public List<UserRoleResponse> userRoleList() {
        return this.userRoleService.userRoleList();
    }
}

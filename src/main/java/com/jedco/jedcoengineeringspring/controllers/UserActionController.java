package com.jedco.jedcoengineeringspring.controllers;

import com.jedco.jedcoengineeringspring.rest.response.UserActionsListByGroupResponse;
import com.jedco.jedcoengineeringspring.services.UserActionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/UserAction")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "UserManagement")
public class UserActionController {
    private final UserActionService userActionService;

    @GetMapping("/userActionList")
    @Operation(summary = "", description = "list of all registered userActions")
    @PreAuthorize("hasAnyAuthority('LIST_ROLES')")
    public List<UserActionsListByGroupResponse> userActionList() {
        return this.userActionService.userActionList();
    }
}

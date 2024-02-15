package com.jedco.jedcoengineeringspring.controllers;

import com.jedco.jedcoengineeringspring.rest.request.PasswordResetRequest;
import com.jedco.jedcoengineeringspring.rest.request.UpdateUserProfileRequest;
import com.jedco.jedcoengineeringspring.rest.request.UpdateUserRoleRequest;
import com.jedco.jedcoengineeringspring.rest.request.UserRegisterRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.UserResponse;
import com.jedco.jedcoengineeringspring.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Users")
public class UserController {
    private final UserService userService;

    @GetMapping("/listUsers")
    public List<UserResponse> listUsers(@RequestParam("start") Integer startPosition, @RequestParam("max") Integer maxResult) {
        return this.userService.listUsers(startPosition, maxResult);
    }

    @PostMapping("/registerUser")
    @PreAuthorize("hasAnyAuthority('REGISTER_USER')")
    public ResponseDto registerUser(@RequestBody UserRegisterRequest registerDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return this.userService.registerUser(registerDto, userDetails.getUsername());
    }

    @PutMapping("/updateUserProfile")
    @Operation(summary = "", description = "updates any information about the user")
    @PreAuthorize("hasAnyAuthority('RESET_USER')")
    public ResponseDto updateUserProfile(@RequestBody UpdateUserProfileRequest entity) {

        return this.userService.updateUserProfile(entity);
    }

    @PutMapping("/updateUserRole")
    @Operation(summary = "", description = "updates any information about the user")
    @PreAuthorize("hasAnyAuthority('RESET_USER')")
    public ResponseDto updateUserRole(@RequestBody UpdateUserRoleRequest entity) {
        return this.userService.updateUserRole(entity);
    }

    @GetMapping("/userListByRole")
    @Operation(summary = "", description = "list of all not deleted users by their role")
    @PreAuthorize("hasAnyAuthority('LIST_USER')")
    public List<UserResponse> userListByRole(@RequestParam("roleId") Long roleId) {
        return this.userService.usersListByRole(roleId);
    }

    @PostMapping("/resetUserPassword")
    @PreAuthorize("hasAnyAuthority('RESET_USER')")
    public ResponseDto resetUserPassword(@RequestBody PasswordResetRequest resetDto) {
        return this.userService.resetUserPassword(resetDto);
    }

    @DeleteMapping("/deleteUser")
    @Operation(summary = "", description = "change the status of the user to deleted")
    @PreAuthorize("hasAnyAuthority('DELETE_USER')")
    public ResponseDto deleteUser(@RequestParam("userId") Long userId) {
        return this.userService.deleteUser(userId);
    }

    @PutMapping("/suspendUser")
    @Operation(summary = "", description = "change the status of user in to suspended. this works only for active users")
    @PreAuthorize("hasAnyAuthority('SUSPEND_USER')")
    public ResponseDto suspendUser(@RequestParam("userId") Long userId) {
        return this.userService.suspendUser(userId);
    }

    @PutMapping("/activateUser")
    @Operation(summary = "", description = "change the status of the user in to active. this works only for suspended users")
    @PreAuthorize("hasAnyAuthority('ACTIVATE_USER')")
    public ResponseDto reactivateUser(@RequestParam("userId") Long userId) {
        return this.userService.reactivateUser(userId);
    }
}

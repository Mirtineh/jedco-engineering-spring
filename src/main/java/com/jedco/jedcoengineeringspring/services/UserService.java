package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.PasswordResetRequest;
import com.jedco.jedcoengineeringspring.rest.request.UpdateUserProfileRequest;
import com.jedco.jedcoengineeringspring.rest.request.UpdateUserRoleRequest;
import com.jedco.jedcoengineeringspring.rest.request.UserRegisterRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> listUsers(Integer startPosition, Integer maxResult);

    ResponseDto registerUser(UserRegisterRequest registerDto, String username);

    ResponseDto updateUserProfile(UpdateUserProfileRequest updateUserProfile);

    ResponseDto updateUserRole(UpdateUserRoleRequest updateRole);

    List<UserResponse> usersListByRole(Long roleId);

    ResponseDto resetUserPassword(PasswordResetRequest resetDto);

    ResponseDto deleteUser(Long userId);

    ResponseDto suspendUser(Long userId);

    ResponseDto reactivateUser(Long userId);
}

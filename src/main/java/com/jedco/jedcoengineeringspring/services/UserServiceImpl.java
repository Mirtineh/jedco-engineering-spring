package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.PasswordResetRequest;
import com.jedco.jedcoengineeringspring.rest.request.UpdateUserProfileRequest;
import com.jedco.jedcoengineeringspring.rest.request.UpdateUserRoleRequest;
import com.jedco.jedcoengineeringspring.rest.request.UserRegisterRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public List<UserResponse> listUsers(Integer startPosition, Integer maxResult) {
        return null;
    }

    @Override
    public ResponseDto registerUser(UserRegisterRequest registerDto, String username) {
        return null;
    }

    @Override
    public ResponseDto updateUserProfile(UpdateUserProfileRequest updateUserProfile) {
        return null;
    }

    @Override
    public ResponseDto updateUserRole(UpdateUserRoleRequest updateRole) {
        return null;
    }

    @Override
    public List<UserResponse> usersListByRole(Long roleId) {
        return null;
    }

    @Override
    public ResponseDto resetUserPassword(PasswordResetRequest resetDto) {
        return null;
    }

    @Override
    public ResponseDto deleteUser(Long userId) {
        return null;
    }

    @Override
    public ResponseDto suspendUser(Long userId) {
        return null;
    }

    @Override
    public ResponseDto reactivateUser(Long userId) {
        return null;
    }
}

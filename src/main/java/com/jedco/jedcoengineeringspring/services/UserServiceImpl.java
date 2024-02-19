package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.mappers.UserMapper;
import com.jedco.jedcoengineeringspring.models.User;
import com.jedco.jedcoengineeringspring.models.UserRole;
import com.jedco.jedcoengineeringspring.repositories.StatusRepository;
import com.jedco.jedcoengineeringspring.repositories.UserRepository;
import com.jedco.jedcoengineeringspring.repositories.UserRoleRepository;
import com.jedco.jedcoengineeringspring.rest.request.PasswordResetRequest;
import com.jedco.jedcoengineeringspring.rest.request.UpdateUserProfileRequest;
import com.jedco.jedcoengineeringspring.rest.request.UpdateUserRoleRequest;
import com.jedco.jedcoengineeringspring.rest.request.UserRegisterRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final StatusRepository statusRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserResponse> listUsers(Integer startPosition, Integer maxResult) {
        Pageable pageable = PageRequest.of(startPosition != null ? startPosition : 0, maxResult != null ? maxResult : Integer.MAX_VALUE);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent().stream().map(userMapper::toUserResponse).toList();

    }

    @Override
    public ResponseDto registerUser(UserRegisterRequest registerDto, String username) {
        try {
            Optional<UserRole> optionalUserRole = userRoleRepository.findById(registerDto.roleId());
            if (optionalUserRole.isEmpty()) {
                return new ResponseDto(false, "Selected User Role Not Found");
            }
            var userRole = optionalUserRole.get();
            Optional<User> optionalRegisteredBy = userRepository.findByUsername(username);
            if (optionalRegisteredBy.isEmpty()) {
                return new ResponseDto(false, "Authentication is Required to Perform this action");
            }
            var registeredBy = optionalRegisteredBy.get();
            User user = new User();

            user.setFirstName(registerDto.firstName());
            user.setLastName(registerDto.lastName());
            user.setPhoneNo(registerDto.phoneNo());
            user.setEmail(registerDto.email());
            user.setUserRole(userRole);
            user.setActivationNo("");
            user.setPasswordIndex(0L);
            user.setRegisteredBy(registeredBy);
            user.setRegisteredDate(new Date());
            user.setStatus(statusRepository.findById(1L).get());
            user.setUsername(registerDto.username());
            user.setPassword(this.passwordEncoder.encode(registerDto.password()));
            user.setPasswordIndex(0L);
            user.setActivationIndex(0L);

            userRepository.save(user);

            return new ResponseDto(true, "User Registered Successfully");


        } catch (Exception ex) {
            return new ResponseDto(false, "Operation Failed due to : " + ex.getMessage());
        }
    }

    @Override
    public ResponseDto updateUserProfile(UpdateUserProfileRequest updateUserProfile) {
        try {
            Long activeStatus = 1L;
            Optional<User> optionalUser = userRepository.findById(updateUserProfile.userId());
            Optional<UserRole> optionalUserRole = userRoleRepository.findByIdAndStatusId(updateUserProfile.userRoleId(), activeStatus);

            if (optionalUser.isPresent() && optionalUser.get().getStatus().getId().equals(1L) && optionalUserRole.isPresent()) {

                var user = optionalUser.get();
                var userRole = optionalUserRole.get();
                user.setFirstName(updateUserProfile.firstName());
                if (updateUserProfile.password() != null && !updateUserProfile.password().isEmpty()) {
                    String hashPassword = this.passwordEncoder.encode(updateUserProfile.password());
                    user.setPassword(hashPassword);
                    user.setPasswordIndex(user.getPasswordIndex() == null ? 0L : user.getPasswordIndex() + 1);
                }
                user.setLastName(updateUserProfile.lastName());
                user.setEmail(updateUserProfile.email());
                user.setPhoneNo(updateUserProfile.phone());
                user.setUserRole(userRole);
//                user.setUpdatedOn(new Date());
                userRepository.save(user);
                return new ResponseDto(true, "User profile updated successfully");
            } else {
                return new ResponseDto(false, "User Not Found!");
            }
        } catch (Exception e) {
            log.error("User profile update failed! ...." + e.getMessage());
            return new ResponseDto(false, "Operation Failed!");
        }
    }

    @Override
    public ResponseDto updateUserRole(UpdateUserRoleRequest updateRole) {
        try {
            Long activeStatus = 1L;
            Optional<User> optionalUser = userRepository.findById(updateRole.userId());
            Optional<UserRole> optionalUserRole = userRoleRepository.findByIdAndStatusId(updateRole.userRoleId(), activeStatus);

            if (optionalUser.isPresent() && optionalUser.get().getStatus().getId().equals(1L) && optionalUserRole.isPresent()) {

                var user = optionalUser.get();
                var userRole = optionalUserRole.get();

                user.setUserRole(userRole);
                userRepository.save(user);
                return new ResponseDto(true, "User profile updated successfully");
            } else {
                return new ResponseDto(false, "User Not Found!");
            }
        } catch (Exception e) {
            log.error("User Role Update failed ..." + e.getMessage());
            return new ResponseDto(false, "Operation Failed!");
        }
    }

    @Override
    public List<UserResponse> usersListByRole(Long roleId) {
        Long deletedStatus = 3L;
        List<User> userList = userRepository.findByUserRoleIdAndStatusIdNot(roleId, deletedStatus);
        return userList
                .stream()
                .filter(user -> user.getUsername() == null || !user.getUsername().equals("admin"))
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public ResponseDto resetUserPassword(PasswordResetRequest resetDto) {
        Optional<User> optionalUser = userRepository.findById(resetDto.userId());
        if (optionalUser.isEmpty()) {
            return new ResponseDto(false, "User Not Found!");
        }
        var user = optionalUser.get();
        if (resetDto.password() == null || resetDto.password().isEmpty()) {
            return new ResponseDto(false, "Password Can Not Be Empty!");
        }
        if (resetDto.confirmPassword() == null || resetDto.confirmPassword().isEmpty()) {
            return new ResponseDto(false, "Confrim Password Can Not Be Empty!");
        }
        if (!resetDto.password().equals(resetDto.confirmPassword())) {
            return new ResponseDto(false, "Confirm Password Did Not Match!");
        }
        String hashPassword = this.passwordEncoder.encode(resetDto.password());
        user.setPassword(hashPassword);
        user.setStatus(statusRepository.findById(1L).get());
        user.setPasswordIndex(user.getPasswordIndex() == null ? 0L : user.getPasswordIndex() + 1);


        userRepository.save(user);
        return new ResponseDto(true, "Password Set Successfully for " + user.getFirstName() + " " + user.getLastName());

    }

    @Override
    public ResponseDto deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            return new ResponseDto(false, "User Not Found!");
        }
        var user = optionalUser.get();
        Long deletedStatus = 3L;
        user.setStatus(this.statusRepository.findById(deletedStatus).get());
        userRepository.save(user);
        return new ResponseDto(true, "User " + user.getFirstName() + " " + user.getLastName() + " Deleted Successfully");
    }

    @Override
    public ResponseDto suspendUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            return new ResponseDto(false, "User Not Found!");
        }
        var user = optionalUser.get();
        Long suspendedStatus = 4L;
        user.setStatus(this.statusRepository.findById(suspendedStatus).get());
        userRepository.save(user);
        return new ResponseDto(true, "User " + user.getFirstName() + " " + user.getLastName() + " Suspended Successfully");
    }

    @Override
    public ResponseDto reactivateUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            return new ResponseDto(false, "User Not Found!");
        }
        var user = optionalUser.get();
        Long activeStatus = 1L;
        user.setStatus(this.statusRepository.findById(activeStatus).get());
        userRepository.save(user);
        return new ResponseDto(true, "User " + user.getFirstName() + " " + user.getLastName() + " Suspended Successfully");
    }
}

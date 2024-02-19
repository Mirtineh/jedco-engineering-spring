package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.mappers.UserMapper;
import com.jedco.jedcoengineeringspring.models.UserRole;
import com.jedco.jedcoengineeringspring.repositories.UserRoleRepository;
import com.jedco.jedcoengineeringspring.rest.request.UserRoleUpdateRequest;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import com.jedco.jedcoengineeringspring.rest.response.UserRoleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseDto userRoleUpdate(UserRoleUpdateRequest userRoleUpdateDto) {
        try {
            Optional<UserRole> optionalUserRole = userRoleRepository.findById(userRoleUpdateDto.id());
            if (optionalUserRole.isEmpty()) {
                return new ResponseDto(false, "User role not found!");
            }
            var userRole = optionalUserRole.get();
            if (userRoleUpdateDto.name() == null) {
                return new ResponseDto(false, "Role name is empty");
            }
            if (userRole.getStatus().getId().equals(1L)) {
                userRole.setRoleName(userRoleUpdateDto.name());
                userRole.setRoleDescription(userRoleUpdateDto.description());
//                userRole.setUpdatedOn(new Date());
                userRoleRepository.save(userRole);
                return new ResponseDto(true, "User role updated successfully");
            } else {
                return new ResponseDto(false, "Deactivated Role");
            }
        } catch (Exception e) {
            log.error("Update User role failed ...." + e.getMessage());
            return new ResponseDto(false, "Operation Failed!");
        }
    }

    @Override
    public List<UserRoleResponse> userRoleList() {
        List<UserRole> userRoleList = userRoleRepository.findAll();
        return userRoleList.stream().map(userMapper::toUserRoleResponse).toList();
    }
}

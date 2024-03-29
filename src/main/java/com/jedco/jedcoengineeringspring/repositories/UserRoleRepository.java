package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    Optional<UserRole> findByRoleName(String s);

    Optional<UserRole> findByIdAndStatusId(Long aLong, Long activeStatus);
}

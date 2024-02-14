package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
}

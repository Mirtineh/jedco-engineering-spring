package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.RoleDefinition;
import com.jedco.jedcoengineeringspring.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDefinitionRepository extends JpaRepository<RoleDefinition,Long> {
    List<RoleDefinition> findByUserRole(UserRole userRole);
}

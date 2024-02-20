package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.RoleDefinition;
import com.jedco.jedcoengineeringspring.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleDefinitionRepository extends JpaRepository<RoleDefinition,Long> {
    List<RoleDefinition> findByUserRole(UserRole userRole);

    List<RoleDefinition> findByUserRoleIdAndStatusId(Long id, Long activeStatus);

    Optional<RoleDefinition> findOneByUserActionIdAndUserRoleId(Long userActionId, Long id);

    List<RoleDefinition> findAllByUserRoleAndStatus_Id(UserRole userRole, long l);
}

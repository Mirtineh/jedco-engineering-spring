package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActionRepository extends JpaRepository<UserAction,Long> {
}

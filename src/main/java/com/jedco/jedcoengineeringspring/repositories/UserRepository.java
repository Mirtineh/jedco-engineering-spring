package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

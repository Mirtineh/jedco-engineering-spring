package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
}

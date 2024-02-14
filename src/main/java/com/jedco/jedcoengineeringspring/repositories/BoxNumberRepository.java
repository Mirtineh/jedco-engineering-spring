package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.BoxNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxNumberRepository extends JpaRepository<BoxNumber,Long> {
}

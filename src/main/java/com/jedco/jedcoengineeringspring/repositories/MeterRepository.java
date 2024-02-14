package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.Meter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterRepository extends JpaRepository<Meter,String> {
}

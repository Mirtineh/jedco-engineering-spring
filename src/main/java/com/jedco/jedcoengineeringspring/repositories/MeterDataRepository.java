package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.MeterData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterDataRepository extends JpaRepository<MeterData,Long> {
}

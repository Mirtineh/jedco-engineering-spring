package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.DispatchedMeter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DispatchedMeterRepository extends JpaRepository<DispatchedMeter,String> {
    List<DispatchedMeter> findByMeterNo(String meterNo);
}

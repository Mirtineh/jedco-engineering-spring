package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.Meter;
import com.jedco.jedcoengineeringspring.models.PoleData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeterRepository extends JpaRepository<Meter,String> {
    Optional<Meter> findByMeterNo(String meterNo);

}

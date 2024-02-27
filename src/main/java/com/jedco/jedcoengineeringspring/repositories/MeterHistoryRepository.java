package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.MeterHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterHistoryRepository extends JpaRepository<MeterHistory,Long> {
}

package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.UpdateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdateHistoryRepository extends JpaRepository<UpdateHistory,Long> {
}

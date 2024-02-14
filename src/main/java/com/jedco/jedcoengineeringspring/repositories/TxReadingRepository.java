package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.TxReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxReadingRepository extends JpaRepository<TxReading,Long> {
}

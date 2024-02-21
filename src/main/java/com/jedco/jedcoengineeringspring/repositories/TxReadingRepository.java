package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.TxReading;
import com.jedco.jedcoengineeringspring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TxReadingRepository extends JpaRepository<TxReading,Long> {
    List<TxReading> findAllByCreatedByAndCreatedOnBetween(User user, Date date, Date date1);
}

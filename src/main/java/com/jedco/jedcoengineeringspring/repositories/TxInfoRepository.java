package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.TxInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxInfoRepository extends JpaRepository<TxInfo,Long> {
}

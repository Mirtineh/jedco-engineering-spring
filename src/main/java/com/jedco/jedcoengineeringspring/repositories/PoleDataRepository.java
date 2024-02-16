package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.PoleData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PoleDataRepository extends JpaRepository<PoleData,Long> {
    Optional<PoleData> findOneByPoleNoAndTxNo(String poleNo, String txNo);
}

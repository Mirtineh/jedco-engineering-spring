package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.TxInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TxInfoRepository extends JpaRepository<TxInfo,Long> {

    Optional<TxInfo> findByTrafoCode(String s);
}

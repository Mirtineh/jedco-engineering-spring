package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.TxInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TxInfoRepository extends JpaRepository<TxInfo,Long> {

    Optional<TxInfo> findByTrafoCode(String s);

    List<String> findDistinctFeederCodes();

    List<TxInfo> findAllByFeederCode(String feeder);

    List<TxInfo> findAllByFeederCodeAndTrafoCode(String feeder, String txCode);

    List<TxInfo> findAllByRegisteredOnBetween(Date date, Date date1);
}

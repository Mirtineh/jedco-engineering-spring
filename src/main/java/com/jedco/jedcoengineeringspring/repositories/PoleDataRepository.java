package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.PoleData;
import com.jedco.jedcoengineeringspring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PoleDataRepository extends JpaRepository<PoleData,Long> {
    Optional<PoleData> findOneByPoleNoAndTxNo(String poleNo, String txNo);

    List<PoleData> findAllBySubmittedOnBetween(Date date, Date date1);

    List<PoleData> findAllByRegisteredByAndSubmittedOnBetween(User user, Date date, Date date1);

    List<PoleData> findAllByFeederAndTxNo(String feeder, String txCode);

    List<PoleData> findByFeederAndTxNoAndPoleNo(String feeder, String txCode, String poleNo);

    List<PoleData> findByPoleNo(String poleNo);

    List<PoleData> findAllByTransformerTrafoCodeAndTransformerFeederCodeAndPoleNo(String feeder, String txCode, String poleNo);

    List<PoleData> findAllByTransformerTrafoCodeAndTransformerFeederCode(String txCode, String feeder);

    Optional<PoleData> findOneByPoleNoAndTransformerTrafoCode(String s, String s1);
}

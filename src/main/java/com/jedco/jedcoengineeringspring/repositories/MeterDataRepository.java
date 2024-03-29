package com.jedco.jedcoengineeringspring.repositories;

import com.jedco.jedcoengineeringspring.models.MeterData;
import com.jedco.jedcoengineeringspring.models.PoleData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeterDataRepository extends JpaRepository<MeterData,Long> {

    List<MeterData> findAllByMeterNoAndStatusId(String meterNo, Long activeStatus);

    MeterData findOneByMeterNoAndStatusId(String meterNo, Long activeStatus);

    List<MeterData> findByPoleDataAndStatusId(PoleData poleData, Long activeStatus);
}

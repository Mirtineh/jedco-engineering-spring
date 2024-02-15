package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.LvDataRegisterRequest;
import com.jedco.jedcoengineeringspring.rest.response.LvDataResponse;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;

import java.util.List;

public interface LvDataService {
    ResponseDto checkMeter(String meterNo);

    List<LvDataResponse> getDataByUser(String date, String username);

    List<LvDataResponse> getDataByFeederAndTx(String feeder, String txCode);

    List<LvDataResponse> getDataByFeederTxPole(String feeder, String txCode, String poleNo);

    List<LvDataResponse> getDataByPoleNo(String poleNo);

    ResponseDto insertLvData(LvDataRegisterRequest lvDataRegisterDto, String username);

    ResponseDto updateLvData(LvDataResponse updateDto, String username);
}

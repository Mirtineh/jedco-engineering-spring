package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.LvDataRegisterRequest;
import com.jedco.jedcoengineeringspring.rest.response.LvDataResponse;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LvDataServiceImpl implements LvDataService {
    @Override
    public ResponseDto checkMeter(String meterNo) {
        return null;
    }

    @Override
    public List<LvDataResponse> getDataByUser(String date, String username) {
        return null;
    }

    @Override
    public List<LvDataResponse> getDataByFeederAndTx(String feeder, String txCode) {
        return null;
    }

    @Override
    public List<LvDataResponse> getDataByFeederTxPole(String feeder, String txCode, String poleNo) {
        return null;
    }

    @Override
    public List<LvDataResponse> getDataByPoleNo(String poleNo) {
        return null;
    }

    @Override
    public ResponseDto insertLvData(LvDataRegisterRequest lvDataRegisterDto, String username) {
        return null;
    }

    @Override
    public ResponseDto updateLvData(LvDataResponse updateDto, String username) {
        return null;
    }
}

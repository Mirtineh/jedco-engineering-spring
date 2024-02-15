package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.CommissioningRegisterRequest;
import com.jedco.jedcoengineeringspring.rest.request.LvExtensionRegisterRequest;
import com.jedco.jedcoengineeringspring.rest.request.MeterRelocationRequest;
import com.jedco.jedcoengineeringspring.rest.request.TxInsertRequest;
import com.jedco.jedcoengineeringspring.rest.response.BoxNumberResponse;
import com.jedco.jedcoengineeringspring.rest.response.DispatchedMeterResponse;
import com.jedco.jedcoengineeringspring.rest.response.RefResponse;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommissioningServiceImpl implements CommissioningService {
    @Override
    public RefResponse checkRef(String meterNo) {
        return null;
    }

    @Override
    public DispatchedMeterResponse checkDispatchedMeter(String meterNo) {
        return null;
    }

    @Override
    public DispatchedMeterResponse checkDispatchedMeterForMeterChange(String meterNo) {
        return null;
    }

    @Override
    public ResponseDto insertCommissioningData(CommissioningRegisterRequest lvDataRegisterDto, String username) {
        return null;
    }

    @Override
    public ResponseDto registerNewTx(TxInsertRequest registerDto, String username) {
        return null;
    }

    @Override
    public ResponseDto updateTx(TxInsertRequest registerDto, String username) {
        return null;
    }

    @Override
    public ResponseDto registerMeterChange(String oldMeter, String newMeter, String username) {
        return null;
    }

    @Override
    public ResponseDto registerMeterRelocation(MeterRelocationRequest relocationDto, String username) {
        return null;
    }

    @Override
    public ResponseDto registerLvExtension(LvExtensionRegisterRequest registerDto, String username) {
        return null;
    }

    @Override
    public List<BoxNumberResponse> getBoxNumbers(Long poleId) {
        return null;
    }
}

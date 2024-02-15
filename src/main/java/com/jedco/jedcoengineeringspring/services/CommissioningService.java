package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.request.CommissioningRegisterRequest;
import com.jedco.jedcoengineeringspring.rest.request.LvExtensionRegisterRequest;
import com.jedco.jedcoengineeringspring.rest.request.MeterRelocationRequest;
import com.jedco.jedcoengineeringspring.rest.request.TxInsertRequest;
import com.jedco.jedcoengineeringspring.rest.response.BoxNumberResponse;
import com.jedco.jedcoengineeringspring.rest.response.DispatchedMeterResponse;
import com.jedco.jedcoengineeringspring.rest.response.RefResponse;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;

import java.util.List;

public interface CommissioningService {
    RefResponse checkRef(String meterNo);

    DispatchedMeterResponse checkDispatchedMeter(String meterNo);

    DispatchedMeterResponse checkDispatchedMeterForMeterChange(String meterNo);

    ResponseDto insertCommissioningData(CommissioningRegisterRequest lvDataRegisterDto, String username);

    ResponseDto registerNewTx(TxInsertRequest registerDto, String username);

    ResponseDto updateTx(TxInsertRequest registerDto, String username);

    ResponseDto registerMeterChange(String oldMeter, String newMeter, String username);

    ResponseDto registerMeterRelocation(MeterRelocationRequest relocationDto, String username);

    ResponseDto registerLvExtension(LvExtensionRegisterRequest registerDto, String username);

    List<BoxNumberResponse> getBoxNumbers(Long poleId);

}

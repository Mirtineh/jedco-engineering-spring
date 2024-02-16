package com.jedco.jedcoengineeringspring.rest.request;

import java.util.List;

public record CommissioningRegisterRequest(
        String feeder,
        String txNo,
        String branchCode,
        String poleNo,
        Long poleId,
        List<LvMeterDataRequest> meterDataDtoList
) {
}

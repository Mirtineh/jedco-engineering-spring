package com.jedco.jedcoengineeringspring.rest.response;

import java.util.List;

public record LvDataResponse(
        Long id,
        Long txId,
        String feeder,
        String txNo,
        String branchCode,
        String poleNo,
        String assemblyType,
        String conductorType,
        String poleType,
        String latitude,
        String longitude,
        String locationAccuracy,
        String poleFeature,
        String remark,
        String pole_anomaly,
        String northing,
        String easting,
        String poleRegistrationType,
        List<LvMeterResponse> meterDataDtoList
) {
}

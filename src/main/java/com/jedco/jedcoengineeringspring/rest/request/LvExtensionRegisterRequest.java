package com.jedco.jedcoengineeringspring.rest.request;

public record LvExtensionRegisterRequest(
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
        String poleRegisterationType
) {
}

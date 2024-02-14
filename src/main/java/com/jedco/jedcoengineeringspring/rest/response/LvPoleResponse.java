package com.jedco.jedcoengineeringspring.rest.response;

public record LvPoleResponse(
        Long id,
        String feeder,
        String txNo,
        String branchCode,
        String poleNo,
        String poleType,
        String northing,
        String easting
) {
}

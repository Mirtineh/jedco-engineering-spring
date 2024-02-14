package com.jedco.jedcoengineeringspring.rest.request;

public record TxInsertRequest(
        Long id,
        String feederCode,
        Double trafoCapacityInKva,
        String trafoCode,
        String mvPoleCodeNearToTrafo,
        Double easting,
        Double northing,
        Double latitude,
        Double longitude
) {
}

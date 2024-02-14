package com.jedco.jedcoengineeringspring.rest.response;

public record TransformerResponse(
        Long id,
        String feeder,
        Double transformerCapacity,
        String trafoCode,
        String mvPoleCode,
        double northing,
        double easting
) {
}

package com.jedco.jedcoengineeringspring.rest.response;

public record PoleResponse(
        Long id,
        String feederCode,
        String trafoCode,
        String poleNo
) {
}

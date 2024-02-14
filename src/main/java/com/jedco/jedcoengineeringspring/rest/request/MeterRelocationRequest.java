package com.jedco.jedcoengineeringspring.rest.request;

public record MeterRelocationRequest(
        String meterNo,
        Long poleId,
        Long boxNoId
) {
}

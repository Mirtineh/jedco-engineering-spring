package com.jedco.jedcoengineeringspring.rest.response;

public record DispatchedMeterResponse(
        boolean status,
        String message,
        String meterNo,
        String name
) {
}

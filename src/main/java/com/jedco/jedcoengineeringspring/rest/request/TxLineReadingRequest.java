package com.jedco.jedcoengineeringspring.rest.request;

import com.jedco.jedcoengineeringspring.config.LineEnum;

public record TxLineReadingRequest(
        LineEnum line,
        Double current,
        Double voltage
) {
}

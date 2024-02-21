package com.jedco.jedcoengineeringspring.rest.response;

import com.jedco.jedcoengineeringspring.config.LineEnum;

public record TxLineReadingResponse(
        Long id,
        LineEnum line,
        Double current,
        Double voltage
) {
}

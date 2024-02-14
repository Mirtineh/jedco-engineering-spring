package com.jedco.jedcoengineeringspring.rest.request;

import java.util.List;

public record TxReadingRequest(
        List<TxLineReadingRequest> lineReadings,
        String branch,
        Double neutralCurrent,
        String remark
) {
}

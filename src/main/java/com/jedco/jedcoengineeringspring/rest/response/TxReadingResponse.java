package com.jedco.jedcoengineeringspring.rest.response;


import java.util.Date;
import java.util.List;

public record TxReadingResponse(
        Long id,
        List<TxLineReadingResponse> lineReadings,
        String branch,
        Double neutralCurrent,
        String remark,
        Date date

) {
}

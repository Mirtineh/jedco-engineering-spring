package com.jedco.jedcoengineeringspring.rest.request;

public record CommissioningRegisterRequest(
        String feeder,
        String txNo,
        String branchCode,
        String poleNo,
        Long poleId
) {
}

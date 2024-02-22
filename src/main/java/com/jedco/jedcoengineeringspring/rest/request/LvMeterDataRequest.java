package com.jedco.jedcoengineeringspring.rest.request;

public record LvMeterDataRequest(
        String customerName,
        String meterNo,
        String comCableLength,
        String serviceCableLength,
        String estimatedLoad,
        String customerType,
        String meterType,
        String connectedPhase,
        String serviceCableType,
        String meterAnomaly,
        String boxNo,
        String assemblyType,
        String ctRatio,
        Long boxNoId
) {
}

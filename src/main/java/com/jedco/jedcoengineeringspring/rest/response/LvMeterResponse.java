package com.jedco.jedcoengineeringspring.rest.response;

public record LvMeterResponse(
        Long id,
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
        String assemblyType,
        Long boxNoId
) {
}

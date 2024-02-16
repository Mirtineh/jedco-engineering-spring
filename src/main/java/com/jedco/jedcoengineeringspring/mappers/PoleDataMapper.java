package com.jedco.jedcoengineeringspring.mappers;

import com.jedco.jedcoengineeringspring.models.MeterData;
import com.jedco.jedcoengineeringspring.models.PoleData;
import com.jedco.jedcoengineeringspring.rest.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PoleDataMapper {
    LvPoleResponse toPoleResponse(PoleData poleData);

    @Mapping(target = "poleRegistrationType", source = "poleRegType")
    @Mapping(target = "meterDataDtoList", source = "meterDataList")
    LvDataResponse toLvDataResponse(PoleData poleData, List<MeterData> meterDataList);

    @Mapping(target = "boxNoId", source = "boxNumber.id")
    @Mapping(target = "assemblyType", source = "boxAssemblyType")
    LvMeterResponse toLvMeterResponse(MeterData meterData);
}

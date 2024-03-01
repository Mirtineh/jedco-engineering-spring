package com.jedco.jedcoengineeringspring.mappers;

import com.jedco.jedcoengineeringspring.models.PoleData;
import com.jedco.jedcoengineeringspring.models.TxInfo;
import com.jedco.jedcoengineeringspring.models.TxReading;
import com.jedco.jedcoengineeringspring.rest.response.PoleResponse;
import com.jedco.jedcoengineeringspring.rest.response.TransformerResponse;
import com.jedco.jedcoengineeringspring.rest.response.TxReadingResponse;
import com.jedco.jedcoengineeringspring.rest.response.TxResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TxMapper {
    TxResponse toTxResponse(TxInfo txInfo);
    @Mapping(target = "trafoCode", source = "transformer.trafoCode")
    @Mapping(target = "feederCode", source = "transformer.feederCode")
    PoleResponse toPoleResponse(PoleData poleData);

    @Mapping(target = "transformerCapacity", source = "trafoCapacityInKva")
    @Mapping(target = "mvPoleCode", source = "mvPoleCodeNearToTrafo")
    @Mapping(target = "feeder", source = "feederCode")
    TransformerResponse toTransformerResponse(TxInfo txInfo);

    @Mapping(target = "date", source = "createdOn")
    TxReadingResponse toTxReadingResponse(TxReading txReading);

}

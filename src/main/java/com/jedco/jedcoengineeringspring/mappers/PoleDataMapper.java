package com.jedco.jedcoengineeringspring.mappers;

import com.jedco.jedcoengineeringspring.models.PoleData;
import com.jedco.jedcoengineeringspring.rest.response.LvPoleResponse;
import com.jedco.jedcoengineeringspring.rest.response.PoleResponse;
import com.jedco.jedcoengineeringspring.rest.response.RefResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PoleDataMapper {
    LvPoleResponse toPoleResponse(PoleData poleData);
}

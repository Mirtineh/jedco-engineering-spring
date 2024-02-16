package com.jedco.jedcoengineeringspring.mappers;

import com.jedco.jedcoengineeringspring.models.BoxNumber;
import com.jedco.jedcoengineeringspring.rest.response.BoxNumberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BoxNumberMapper {
    BoxNumberResponse toBoxNumberResponse(BoxNumber boxNumber);
}

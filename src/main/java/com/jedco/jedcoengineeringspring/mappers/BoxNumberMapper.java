package com.jedco.jedcoengineeringspring.mappers;

import com.jedco.jedcoengineeringspring.models.BoxNumber;
import com.jedco.jedcoengineeringspring.rest.response.BoxNumberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BoxNumberMapper {
    @Mapping(target = "boxNumber", source = "box", qualifiedByName = "mapBoxNumber")
    BoxNumberResponse toBoxNumberResponse(BoxNumber box);

    @Named("mapBoxNumber")
    default String mapBoxNumber(BoxNumber boxNumber){
        return boxNumber.getFormattedBoxNumber();
    }
}

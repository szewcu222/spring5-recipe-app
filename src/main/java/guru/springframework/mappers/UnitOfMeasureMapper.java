package guru.springframework.mappers;

import guru.springframework.dtos.UnitOfMeasureDTO;
import guru.springframework.domain.UnitOfMeasure;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UnitOfMeasureMapper {
    UnitOfMeasureMapper MAPPER = Mappers.getMapper(UnitOfMeasureMapper.class);

    UnitOfMeasureDTO unitOfMeasureToDto(UnitOfMeasure unitOfMeasure, @Context CycleAvoidingMappingContext context);
    @InheritInverseConfiguration
    UnitOfMeasure dtoToUnitOfMeasure(UnitOfMeasureDTO unitOfMeasureDTO, @Context CycleAvoidingMappingContext context);
}

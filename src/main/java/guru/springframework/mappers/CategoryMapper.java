package guru.springframework.mappers;

import guru.springframework.dtos.CategoryDTO;
import guru.springframework.domain.Category;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface CategoryMapper {
    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToDto(Category category, @Context CycleAvoidingMappingContext context);
    @InheritInverseConfiguration
    Category dtoToCategory(CategoryDTO categoryDTO, @Context CycleAvoidingMappingContext context);
}

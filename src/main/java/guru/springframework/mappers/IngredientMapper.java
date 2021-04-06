package guru.springframework.mappers;

import guru.springframework.dtos.IngredientDTO;
import guru.springframework.domain.Ingredient;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {
    IngredientMapper MAPPER = Mappers.getMapper(IngredientMapper.class);

    IngredientDTO ingredientToDto(Ingredient ingredient, @Context CycleAvoidingMappingContext context);
    @InheritInverseConfiguration
    Ingredient dtoToIngredient(IngredientDTO ingredientDTO, @Context CycleAvoidingMappingContext context);
}

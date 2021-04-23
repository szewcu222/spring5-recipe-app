package guru.springframework.mappers;

import guru.springframework.dtos.IngredientDTO;
import guru.springframework.domain.Ingredient;
import guru.springframework.services.IngredientService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface IngredientMapper {
//    IngredientMapper MAPPER = Mappers.getMapper(IngredientMapper.class);


//    @Mapping(source = "recipe", target = "recipe", ignore = true)
    IngredientDTO ingredientToDto(Ingredient ingredient, @Context CycleAvoidingMappingContext context);
    @InheritInverseConfiguration
    Ingredient dtoToIngredient(IngredientDTO ingredientDTO, @Context CycleAvoidingMappingContext context);

    @DoIgnore
    default IngredientDTO ingredientToDtoWithContext(Ingredient ingredient) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return this.ingredientToDto(ingredient, context);
    }

}

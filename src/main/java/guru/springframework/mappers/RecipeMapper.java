package guru.springframework.mappers;

import guru.springframework.dtos.RecipeDTO;
import guru.springframework.domain.Recipe;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {
    RecipeMapper MAPPER = Mappers.getMapper( RecipeMapper.class );

    RecipeDTO recipeToDto(Recipe recipe, @Context CycleAvoidingMappingContext context);
    @InheritInverseConfiguration
    Recipe dtoToRecipe(RecipeDTO recipeDTO, @Context CycleAvoidingMappingContext context);
}

package guru.springframework.services;

import guru.springframework.domain.Ingredient;
import guru.springframework.dtos.IngredientDTO;
import guru.springframework.mappers.CycleAvoidingMappingContext;
import guru.springframework.mappers.IngredientMapper;
import guru.springframework.mappers.IngredientMapperImpl;
import guru.springframework.repositories.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper mapper;

    public IngredientService(IngredientRepository ingredientRepository, IngredientMapper mapper) {
        this.ingredientRepository = ingredientRepository;
        this.mapper = mapper;
    }

    public Set<Ingredient> getIngredientsByRecipeId(Long recipeId) {
        return ingredientRepository.findByRecipe_IdOrderByIdAsc(recipeId);
    }

    public Set<IngredientDTO> getIngredientsDTOByRecipeId(Long recipeId) {
        Set<Ingredient> ingredients = this.getIngredientsByRecipeId(recipeId);
        Set<IngredientDTO> ingredientDTOS = new HashSet<>();

        ingredients.forEach(ing -> ingredientDTOS.add(mapper.ingredientToDtoWithContext(ing)));

        return ingredientDTOS;
    }

    public Optional<Ingredient> getIngredientForRecipe(Long recipeId, Long ingredientId) {
        return ingredientRepository.findByRecipe_IdAndId(recipeId, ingredientId);
    }

    public IngredientDTO getIngredientDTOForRecipe(Long recipeId, Long ingredientId) {
        return mapper.ingredientToDtoWithContext(getIngredientForRecipe(recipeId, ingredientId).orElse(null));
    }

    public IngredientDTO saveIngredientDto(IngredientDTO ingredientDTO) {
        Ingredient savedIngredient = ingredientRepository.save(mapper.dtoToIngredient(ingredientDTO, new CycleAvoidingMappingContext()));
        return mapper.ingredientToDto(savedIngredient, new CycleAvoidingMappingContext());
    }

    public void deleteIngredientById(Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}

package guru.springframework.services;

import guru.springframework.dtos.RecipeDTO;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Set<RecipeDTO> getRecipesDTO();
    Recipe getRecipeById(Long id);
    RecipeDTO getRecipeDTOByID(Long id);
    Recipe saveRecipe(Recipe recipe);
    RecipeDTO saveRecipeDto(RecipeDTO recipeDto);

    void deleteRecipeById(Long id);
}

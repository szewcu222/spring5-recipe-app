package guru.springframework.services;

import guru.springframework.dtos.RecipeDTO;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe getRecipeById(Long id);
    Set<RecipeDTO> getRecipesDTO();
}

package guru.springframework.services;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
//    private final IngredientRepository ingredientRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        // Recipe recipe = recipes.stream().findFirst().get();
        // Set<Ingredient> ingredients = ingredientRepository.findIngredientsByRecipe_Id(recipe.getId());

        Set<Recipe> recByDiffAndCookTime = recipeRepository.findRecipesByDifficultyOrCookTime(Difficulty.EASY, 0);

        // Set<Recipe> recByIngredients = recipeRepository.findRecipesByIngredients(ingredients);

        System.out.println("findRecipeByNoteIn");
        // Recipe recByNoteId = recipeRepository.findRecipeByNoteIn(Collections.singletonList(recipe.getNote()));

        Set<Recipe> allByCategoriesId = recipeRepository.findByCategoriesId(1L);

        Set<Recipe> allByIgredientId = recipeRepository.findRecipesByIngredientsId(1L);
        return recipes;
    }
}

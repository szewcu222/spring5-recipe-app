package guru.springframework.repositories;

import guru.springframework.domain.*;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;


public interface RecipeRepository extends CrudRepository<Recipe, Long> {

//    Set<Recipe> findByCategories(Long categories_id);
    Set<Recipe> findRecipesByDifficultyOrCookTime(Difficulty difficulty, Integer cookTime);
    Set<Recipe> findRecipesByIngredients(Set<Ingredient> ingredients);
    Set<Recipe> findRecipesByIngredientsId(Long ingredients_id);

    Set<Recipe> findByIngredientsOrNote_Id(Set<Ingredient> ingredients, Long note_id);
    Recipe findRecipeByNoteIn(Collection<Note> note);
    Set<Recipe> findByCategoriesId(Long categories_id);
}

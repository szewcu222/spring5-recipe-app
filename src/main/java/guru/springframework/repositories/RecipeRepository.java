package guru.springframework.repositories;

import guru.springframework.domain.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;


public interface RecipeRepository extends CrudRepository<Recipe, Long> {

//    Set<Recipe> findByCategories(Long categories_id);
    @Query("select r from Recipe r where r.id = :id")
    Optional<Recipe> findRecipeById(Long id);
//    Optional<Recipe> findRecipeByIdOrderByIngredientsId(Long id);
//    Optional<Recipe> findById(Long id, Sort sort);
//    Optional<Recipe> findById_ingredientsOrderById(Long aLong);

//    Set<Recipe> findRecipesByDifficultyOrCookTime(Difficulty difficulty, Integer cookTime);
//    Set<Recipe> findRecipesByIngredients(Set<Ingredient> ingredients);
//    Set<Recipe> findRecipesByIngredientsId(Long ingredients_id);
//
//    Set<Recipe> findByIngredientsOrNote_Id(Set<Ingredient> ingredients, Long note_id);
//    Recipe findRecipeByNoteIn(Collection<Note> note);
//    Set<Recipe> findByCategoriesId(Long categories_id);
}

package guru.springframework.repositories;

import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Set<Ingredient> findIngredientsByRecipe_Id(Long recipeId);

    Set<Ingredient> findByRecipe_IdOrderByIdAsc(Long recipeId);

    Optional<Ingredient> findByRecipe_IdAndId(Long recipeId, Long ingredientId);


}


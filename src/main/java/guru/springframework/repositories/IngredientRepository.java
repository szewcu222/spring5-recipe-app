package guru.springframework.repositories;

import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Set<Ingredient> findIngredientsByRecipe_Id(Long recipeId);

}


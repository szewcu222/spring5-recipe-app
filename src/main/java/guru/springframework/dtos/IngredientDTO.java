package guru.springframework.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientDTO {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureDTO uom;
    private RecipeDTO recipe;

//    public Long getRecipeId() {
//        return this.recipe.getId();
//    }
}

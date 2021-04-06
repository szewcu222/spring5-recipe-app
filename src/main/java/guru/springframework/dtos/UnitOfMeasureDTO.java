package guru.springframework.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasureDTO {
    private Long id;
    private String description;
    private IngredientDTO ingredient;
}

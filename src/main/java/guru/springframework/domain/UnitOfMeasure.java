package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "ingredient")
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne
    private Ingredient ingredient;
}

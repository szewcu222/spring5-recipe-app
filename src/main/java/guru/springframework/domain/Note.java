package guru.springframework.domain;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

//@Data
@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String recipeNotes;

    @OneToOne
    private Recipe recipe;
}

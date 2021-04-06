package guru.springframework.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteDTO{
    private Long id;
    private String recipeNotes;
    private RecipeDTO recipe;

//    @Override
//    public Note convert(NoteDTO noteDTO) {
//        Note note = new Note();
//        note.setId(this.getId());
//        note.setRecipeNotes(this.getRecipeNotes());
//        return note;
//    }

}

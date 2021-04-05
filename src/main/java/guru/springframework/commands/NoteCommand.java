package guru.springframework.commands;

import guru.springframework.domain.Note;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;

@Getter
@Setter
@NoArgsConstructor
public class NoteCommand implements Converter<NoteCommand, Note> {
    private Long id;
    private String recipeNotes;
    private RecipeCommand recipe;

    @Override
    public Note convert(NoteCommand noteCommand) {
        Note note = new Note();
        note.setId(this.getId());
        note.setRecipeNotes(this.getRecipeNotes());
        return note;
    }

}

package guru.springframework.repositories;

import guru.springframework.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByRecipe_Note_Id(Long id);

    Set<Note> findByRecipe_Note_IdIn(Collection<Long> ids);

}
package guru.springframework.mappers;

import guru.springframework.dtos.NoteDTO;
import guru.springframework.domain.Note;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoteMapper {
    NoteMapper MAPPER = Mappers.getMapper(NoteMapper.class);

    NoteDTO noteToDto(Note note, @Context CycleAvoidingMappingContext context);
    @InheritInverseConfiguration
    Note dtoToNote(NoteDTO noteDTO, @Context CycleAvoidingMappingContext context);
}

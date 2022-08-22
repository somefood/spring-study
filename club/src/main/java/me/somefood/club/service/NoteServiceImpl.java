package me.somefood.club.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.somefood.club.dto.NoteDTO;
import me.somefood.club.entity.Note;
import me.somefood.club.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public Long register(NoteDTO noteDTO) {
        Note note = dtoToEntity(noteDTO);

        log.info("==================");
        log.info("{}", note);

        noteRepository.save(note);

        return note.getNum();
    }

    @Override
    public NoteDTO get(Long num) {
        Optional<Note> result = noteRepository.getWithWriter(num);

        if (result.isPresent()) {

            return entityToDTO(result.get());
        }
        return null;
    }

    @Override
    public void modify(NoteDTO noteDTO) {
        Long num = noteDTO.getNum();

        Optional<Note> result = noteRepository.findById(num);

        if (result.isPresent()) {
            Note note = result.get();

            note.changeTitle(note.getTitle());
            note.changeContent(note.getContent());
            noteRepository.save(note);
        }
    }

    @Override
    public void remove(Long num) {

        noteRepository.deleteById(num);
    }

    @Override
    public List<NoteDTO> getAllWithWriter(String writerEmail) {
        List<Note> noteList = noteRepository.getList(writerEmail);

        return noteList.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}

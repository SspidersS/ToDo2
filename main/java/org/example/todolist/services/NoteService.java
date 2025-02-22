package org.example.todolist.services;

import org.example.todolist.enteties.Note;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private Map<Long, Note> noteStorage = new HashMap<>();
    private Random random = new Random();

    public NoteService() {
        noteStorage.put(1L, new Note(1L,"test", "test"));
        noteStorage.put(2L, new Note(2L,"test2", "test2"));
        noteStorage.put(3L, new Note(3L, "test3", "test3"));
    }

    public List<Note> listAll() {
        return new ArrayList<>(noteStorage.values());
    }

    public Note add(Note note) {
        long id = generateUniqueId();
        note.setId(id);
        noteStorage.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (noteStorage.containsKey(id)) {
            noteStorage.remove(id);
        } else {
            throw new NoSuchElementException("Note with id " + id + " not found.");
        }
    }

    public void update(Note note) {
        if (noteStorage.containsKey(note.getId())) {
            Note existingNote = noteStorage.get(note.getId());
            existingNote.setTitle(note.getTitle());
            existingNote.setContent(note.getContent());
        } else {
            throw new NoSuchElementException("Note with id " + note.getId() + " not found.");
        }
    }

    public Note getById(long id) {
        if (noteStorage.containsKey(id)) {
            return noteStorage.get(id);
        } else {
            throw new NoSuchElementException("Note with id " + id + " not found.");
        }
    }

    private long generateUniqueId() {
        long id;
        do {
            id = Math.abs(random.nextLong());
        } while (noteStorage.containsKey(id));
        return id;
    }
}

package org.example.todolist.services;

import org.example.todolist.model.Note;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private Map<Long, Note> noteStorage = new HashMap<>();
    private static Random random = new Random();

    public NoteService() {
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

package com.note.app.NotesApp.dao;

import com.note.app.NotesApp.entities.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteDao extends CrudRepository<Note, Long> {
}

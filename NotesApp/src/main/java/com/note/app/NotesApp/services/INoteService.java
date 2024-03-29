package com.note.app.NotesApp.services;

import com.note.app.NotesApp.entities.Category;
import com.note.app.NotesApp.entities.Note;
import com.note.app.NotesApp.entities.User;

import java.util.List;

public interface INoteService {

    Note save(Note note);

    List<Note> findAll();

    List<Note> findByUserActives(User user);

    List<Note> findUserByArchived(User user);

    Note findById(Long id);

    Note findByCategory(Category category);

    void delete(Note note);

    void update(Note note);

    void updateCategory(Note note);

    List<Note> findAllByCategory(Category category);

}

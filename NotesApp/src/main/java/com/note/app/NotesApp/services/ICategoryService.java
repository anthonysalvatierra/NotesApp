package com.note.app.NotesApp.services;

import com.note.app.NotesApp.entities.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();

    Category save(Category category);

    Category findById(Long id);

    void delete(Category category);

    Category findByName(String name);

}

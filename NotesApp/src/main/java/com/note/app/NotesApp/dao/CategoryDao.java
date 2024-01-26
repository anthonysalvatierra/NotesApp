package com.note.app.NotesApp.dao;

import com.note.app.NotesApp.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Long> {
}

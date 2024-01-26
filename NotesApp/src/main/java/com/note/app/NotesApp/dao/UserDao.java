package com.note.app.NotesApp.dao;

import com.note.app.NotesApp.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

}

package com.note.app.NotesApp.services;

import com.note.app.NotesApp.entities.User;

public interface IUserService {

    User findByUsername(String username);

    User save(User user);

}

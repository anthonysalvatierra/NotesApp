package com.note.app.NotesApp.services;

import com.note.app.NotesApp.dao.UserDao;
import com.note.app.NotesApp.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {

        return  em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User save(User user) {
        return this.userDao.save(user);
    }
}

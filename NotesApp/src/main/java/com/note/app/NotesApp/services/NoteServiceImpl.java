package com.note.app.NotesApp.services;

import com.note.app.NotesApp.dao.NoteDao;
import com.note.app.NotesApp.entities.Category;
import com.note.app.NotesApp.entities.Note;
import com.note.app.NotesApp.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteServiceImpl  implements INoteService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private NoteDao noteDao;

    @Override
    @Transactional
    public Note save(Note note) {
        return noteDao.save(note);
    }

    @Override
    public List<Note> findAll() {
        return (List<Note>) this.noteDao.findAll();
    }

    @Override
    public List<Note> findByUserActives(User user) {
        return this.em.createQuery("SELECT n FROM Note n WHERE n.user.id = :id AND n.archived = false", Note.class)
                .setParameter("id", user.getId())
                .getResultList();
    }

    @Override
    public List<Note> findUserByArchived(User user){
        return this.em.createQuery("SELECT n FROM Note n WHERE n.user.id = :id AND n.archived = true", Note.class)
                .setParameter("id", user.getId())
                .getResultList();
    }

    @Override
    public Note findById(Long id) {
        return this.noteDao.findById(id).orElse(null);
    }

    @Override
    public Note findByCategory(Category category) {

        Note note;

        try{

            note = this.em.createQuery("SELECT n FROM Note n WHERE n.category = :category", Note.class)
                    .setParameter("category", category)
                    .getSingleResult();

        }catch (NoResultException noResultException){

            note = null;

        }
        return note;
    }

    @Override
    public void delete(Note note) {
        this.noteDao.delete(note);
    }

    @Override
    @Transactional
    public void update(Note note) {
        this.em.createQuery("UPDATE Note n SET n.name= :name, n.description =:description WHERE n.id = :id ")
                .setParameter("name", note.getName())
                .setParameter("description", note.getDescription())
                .setParameter("id", note.getId()).executeUpdate();
    }

    @Override
    @Transactional
    public void updateCategory(Note note) {
        this.em.createQuery("UPDATE Note n SET n.category= :category WHERE n.id = :id ")
                .setParameter("category", note.getCategory())
                .setParameter("id", note.getId()).executeUpdate();
    }

    @Override
    public List<Note> findAllByCategory(Category category) {
        return this.em.createQuery("SELECT n FROM Note n WHERE n.category.name = :name", Note.class)
                .setParameter("name", category.getName())
                .getResultList();
    }


}

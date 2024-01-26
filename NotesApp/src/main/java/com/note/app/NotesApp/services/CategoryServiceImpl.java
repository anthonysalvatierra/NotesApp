package com.note.app.NotesApp.services;

import com.note.app.NotesApp.dao.CategoryDao;
import com.note.app.NotesApp.entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryDao.findAll();
    }

    @Override
    public Category save(Category category) {
        return this.categoryDao.save(category);
    }

    @Override
    public Category findById(Long id) {
        return this.categoryDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Category category) {
        this.categoryDao.delete(category);
    }

    @Override
    public Category findByName(String name) {

        Category category;

        try{

            category = this.em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
                    .setParameter("name", name)
                    .getSingleResult();

        }catch(NoResultException noResultException){

            category = null;

        }
        return category;
    }
}

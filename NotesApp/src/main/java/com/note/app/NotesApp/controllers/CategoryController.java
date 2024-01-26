package com.note.app.NotesApp.controllers;

import com.note.app.NotesApp.entities.Category;
import com.note.app.NotesApp.entities.Note;
import com.note.app.NotesApp.services.ICategoryService;
import com.note.app.NotesApp.services.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private INoteService noteService;

    @RequestMapping("/save")
    public String save(Model model, @RequestParam String name){
        Category category = new Category();
        category.setName(name);
        this.categoryService.save(category);

        return "redirect:/note/redi";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        Category category = this.categoryService.findById(Long.parseLong(id));

        if(category == null){
            return "redirect:/note/redi";
        }

        List<Note> notes = this.noteService.findAllByCategory(category);

        if(notes != null){
            notes.forEach(n -> n.setCategory(null));
            notes.forEach(n -> this.noteService.save(n));
        }

        this.categoryService.delete(category);
        return "redirect:/note/redi";

    }

}

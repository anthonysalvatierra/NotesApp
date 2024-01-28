package com.note.app.NotesApp.controllers;

import com.note.app.NotesApp.entities.Category;
import com.note.app.NotesApp.services.ICategoryService;
import com.note.app.NotesApp.services.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private INoteService noteService;

    @RequestMapping("/save")
    public String save(@RequestParam String name){
        Category category = new Category();
        category.setName(name);
        this.categoryService.save(category);

        return "redirect:/note/redi";
    }

}

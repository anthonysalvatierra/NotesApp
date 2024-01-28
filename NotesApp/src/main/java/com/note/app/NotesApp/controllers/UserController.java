package com.note.app.NotesApp.controllers;

import com.note.app.NotesApp.entities.Category;
import com.note.app.NotesApp.entities.Note;
import com.note.app.NotesApp.entities.User;
import com.note.app.NotesApp.services.ICategoryService;
import com.note.app.NotesApp.services.INoteService;
import com.note.app.NotesApp.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reg")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private INoteService noteService;

    @RequestMapping("/save")
    public String save(@RequestParam String name,
                       @RequestParam String username,
                       @RequestParam String password){

        User user = new User();

        user.setName(name);
        user.setUsername(username);
        user.setPassword(this.encoder.encode(password));

        User userCreated = this.userService.save(user);
        System.out.println("user created: " + userCreated.getName());
        return "redirect:/";
    }

    @RequestMapping("/enter")
    public String enter(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = this.userService.findByUsername(username);

        List<Note> notes = this.noteService.findByUser(user);

        if(notes == null || notes.size() == 0 ||
                notes.get(0).getName() == null || notes.get(0).getName().equals("")){

            model.addAttribute("notes", null);
        }


        List<Category> categories = this.categoryService.findAll();
        Note note = new Note();

        model.addAttribute("isInCategorized", false);
        model.addAttribute("note", note);
        model.addAttribute("categories", categories);
        model.addAttribute("notes", notes);

        return "dashboard";
    }

}

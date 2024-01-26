package com.note.app.NotesApp.controllers;

import com.note.app.NotesApp.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entry")
public class IndexController {

    @RequestMapping("/register")
    public String index(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("title", "Register");
        return "/register";
    }

    @RequestMapping("/enter")
    public String enter(){
        return "dashboard";
    }
}

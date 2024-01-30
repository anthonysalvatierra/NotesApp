package com.note.app.NotesApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entry")
public class IndexController {

    @RequestMapping("/register")
    public String index(Model model){
        model.addAttribute("title", "Register");
        return "register";
    }

}

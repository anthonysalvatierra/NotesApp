package com.note.app.NotesApp;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(UsernameNotFoundException.class)
    public String notFoundUser(){
        return "redirect:/";
    }

}

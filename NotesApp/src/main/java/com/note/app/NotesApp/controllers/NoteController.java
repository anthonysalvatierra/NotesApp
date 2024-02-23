package com.note.app.NotesApp.controllers;

import com.note.app.NotesApp.entities.Category;
import com.note.app.NotesApp.entities.Note;
import com.note.app.NotesApp.entities.User;
import com.note.app.NotesApp.services.ICategoryService;
import com.note.app.NotesApp.services.INoteService;
import com.note.app.NotesApp.services.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private INoteService noteService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/save")
    public String save(Model model, @RequestParam String name,
                                    @RequestParam String description, HttpServletRequest request){

        if(name.equals("") && description.equals("")){
            return "redirect:/note/redi";
        }

        Note note = new Note();
        note.setName(name);
        note.setDescription(description);

        String nameCategory = request.getParameter("category");

        Category categoryParse = new Category();
        categoryParse.setName(nameCategory);
        User user = findUserDetails();
        note.setUser(user);
        Note noteCreated;

        boolean isNameInCategory = this.categoryService.findAll()
                .stream().anyMatch(c -> c.getName().equals(categoryParse.getName()));

        if(!(isNameInCategory)){
            note.setCategory(null);
            noteCreated = this.noteService.save(note);

        }else{

            Category categoryFound = this.categoryService.findByName(nameCategory);
            note.setCategory(categoryFound);
            noteCreated = this.noteService.save(note);
        }

        model.addAttribute("note", noteCreated);

        return "redirect:/note/redi";
    }

    @RequestMapping("/redi")
    public String redi(Model model){
        Note note = (Note) model.getAttribute("note");
        User user = findUserDetails();

        List<Note> notes = this.noteService.findByUserActives(user);
        List<Note> notesArchived = this.noteService.findUserByArchived(user);
        List<Category> categories = this.categoryService.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("notes", notes);
        model.addAttribute("notesArchived", notesArchived);
        model.addAttribute("note", note);
        model.addAttribute("title", "NotesApp");
        return "dashboard";
    }

    @RequestMapping("/archived/{id}")
    public String archived(@PathVariable String id){
        Note note = this.noteService.findById(Long.parseLong(id));
        note.setArchived(true);
        this.noteService.save(note);

        return "redirect:/note/redi";
    }

    @RequestMapping("/unarchived/{id}")
    public String unarchived(@PathVariable String id){
        Note note = this.noteService.findById(Long.parseLong(id));
        note.setArchived(false);
        this.noteService.save(note);

        return "redirect:/note/rediArchived";
    }

    @RequestMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable String id){
        Note note = this.noteService.findById(Long.parseLong(id));
        note.setCategory(null);
        this.noteService.save(note);

        return "redirect:/note/redi";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        Note note = this.noteService.findById(Long.parseLong(id));
        this.noteService.delete(note);

        return "redirect:/note/redi";
    }

    @RequestMapping("/actives")
    public String actives(Model model){
        return "redirect:/note/redi";
    }

    @RequestMapping("/archived")
    public String archived(Model model){
        return "redirect:/note/rediArchived";
    }

    @RequestMapping("/rediArchived")
    public String rediArchived(Model model){
        User user = findUserDetails();
        Note note = new Note();

        List<Note> notes = this.noteService.findUserByArchived(user);
        List<Category> categories = this.categoryService.findAll();

        model.addAttribute("note", note);
        model.addAttribute("notesArchived", notes);
        model.addAttribute("categories", categories);
        model.addAttribute("title", "NotesApp");

        return "archived";
    }

    @RequestMapping("/addCategory/{id}")
    public String addCategory(@PathVariable String id, Model model){
        Note note = this.noteService.findById(Long.parseLong(id));
        List<Category> categories = this.categoryService.findAll();

        model.addAttribute("note", note);
        model.addAttribute("categories", categories);
        model.addAttribute("title", "Add Category");

        return "addCategory";
    }

    @RequestMapping("/changeCategory")
    public String chageCategory(@RequestParam String category, @RequestParam String idNote){

        Note noteFound = this.noteService.findById(Long.parseLong(idNote));

        if(category.equals("")){
            return "redirect:/note/redi";
        }

        Category categoryFound = this.categoryService.findByName(category);
        noteFound.setCategory(categoryFound);
        this.noteService.updateCategory(noteFound);

        return "redirect:/note/redi";

    }
    
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Note note = this.noteService.findById(Long.parseLong(id));
        model.addAttribute("note", note);
        model.addAttribute("title", "Edit");

        return "edit";
    }

    @RequestMapping("/update")
    public String update(Note note){
        this.noteService.update(note);

        return "redirect:/note/redi";
    }

    @RequestMapping("/filter/{id}")
    public String filter(@PathVariable String id, Model model){
        Category category = this.categoryService.findById(Long.parseLong(id));

        return "redirect:/note/filterCategory/" + category.getId();
    }

    @RequestMapping("/filterCategory/{id}")
    public String filterCategory(@PathVariable String id, Model model){
        Category category = this.categoryService.findById(Long.parseLong(id));
        List<Category> categories = this.categoryService.findAll();
        List<Note> notes = this.noteService.findAllByCategory(category);
        List<Note> notesArchived = this.noteService.findUserByArchived(findUserDetails());

        model.addAttribute("categories", categories);
        model.addAttribute("notes", notes);
        model.addAttribute("notesArchived", notesArchived);
        model.addAttribute("title", "NotesApp");

        return "dashboard";
    }

    public User findUserDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = this.userService.findByUsername(username);
        return user;
    }

}

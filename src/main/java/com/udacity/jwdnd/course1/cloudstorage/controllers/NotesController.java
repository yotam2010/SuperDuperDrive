package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/note")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public String getNote(Model model){
        model.addAttribute("noteList",noteService.getNotesOfUser());
        return "home";
    }

    @PostMapping
    public String createNote(@ModelAttribute("noteModel")NoteModel noteModel, Model model){
        noteService.createNote(noteModel);
        model.addAttribute("noteList",noteService.getNotesOfUser());
        return "home";
    }


}

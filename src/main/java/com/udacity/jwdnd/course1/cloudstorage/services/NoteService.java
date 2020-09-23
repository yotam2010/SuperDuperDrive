package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    UserService userService;

    @Autowired
    NoteMapper noteMapper;

    public void createNote(NoteModel noteModel) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        noteModel.setUserid(userService.getUserByName(userName).getUserid());
        noteMapper.createNote(noteModel);

    }

    public List<NoteModel> getNotesOfUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userService.getUserByName(userName).getUserid();

        return noteMapper.getNotesByUserID(userId);

    }
}

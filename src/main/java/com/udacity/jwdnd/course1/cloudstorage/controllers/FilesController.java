package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FilesController {

    @Autowired
    private FileService fileService;

    @GetMapping
    public String getFile(Model model){
        model.addAttribute("filesList",fileService.getFilesOfUser());
        return "home";
    }

    @PostMapping
    public String createFile(@RequestParam("fileUpload") MultipartFile multipartFile, Model model){
        fileService.uploadFile(multipartFile);
        model.addAttribute("filesList",fileService.getFilesOfUser());
        return "home";
    }

    @DeleteMapping("/{fileName}")
    public String deleteFile(@PathVariable("fileName") String fileName, Model model){
        fileService.deleteFile(fileName);
        model.addAttribute("filesList",fileService.getFilesOfUser());
        return "home";
    }

}

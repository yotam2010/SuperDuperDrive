package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private UserService userService;

    @Autowired
    private FileMapper fileMapper;

    public Object uploadFile(MultipartFile multipartFile){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path filePath = Path.of("users-upload").resolve(userName);
        byte[] file;

        try{
            if(!filePath.toFile().exists()){
                Files.createDirectories(filePath);
            }

            if(multipartFile.isEmpty()){
                throw new RuntimeException("The file is empty");
            }

            if(fileName.contains("..")){
                throw new RuntimeException("The file path is security risk");
            }

            try(InputStream inputStream = multipartFile.getInputStream()){
                Files.copy(inputStream, filePath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            }

            file=multipartFile.getBytes();
        }catch (IOException ioException){
            System.out.println(ioException.getStackTrace());
            return ioException.getMessage();
        }

        Integer userid = userService.getUserByName(userName).getUserid();
        return saveFileToDB(fileName,multipartFile.getContentType(),multipartFile.getSize(),userid,file);
    }

    private Object saveFileToDB(String fileName, String contentType, long size, Integer userid, byte[] file) {
        return fileMapper.addFile(fileName,contentType,String.valueOf(size),userid,file);
    }

    public List<FileModel> getFilesOfUser(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userService.getUserByName(userName).getUserid();

        return fileMapper.getFilesByUserID(userId);
    }

    public Integer deleteFile(String fileName) {
        Optional<FileModel> file= getFilesOfUser().stream().filter(fileModel -> fileName.equals(fileName)).findAny();

        if(!file.isPresent()){
            return null;
        }

        Integer fileId = fileMapper.deleteFileById(file.get().getFileId());

        boolean deleted = Path.of("users-upload").resolve("yotam").resolve(fileName).toFile().delete();

        return deleted ? fileId : null;
    }
}

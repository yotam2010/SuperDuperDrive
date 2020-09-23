package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HashService hashService;

    public User getUserByName(String name){
        return userMapper.getUserByName(name);
    }

    public Integer createUser(SignupForm signupForm) {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        String saltString = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(signupForm.getPassword(),saltString);

        return userMapper.createUser(signupForm.getUsername(),hashedPassword,saltString,signupForm.getFirstName(),signupForm.getLastName());
    }
}

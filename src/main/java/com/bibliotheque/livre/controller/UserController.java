package com.bibliotheque.livre.controller;

import com.bibliotheque.livre.model.User;
import com.bibliotheque.livre.data.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.java.Log;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Log
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RestController
@RequestMapping(value="api/user") // This means URL's start with /demo (after Application path)

public class UserController {

    UserRepository userRepository;

    @GetMapping(value="/get")
    public ResponseEntity<List<User>> getAllUser(){
        List<User>users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        log.info("la liste des utilisateurs est bel et bien renvoye");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @SneakyThrows
    @PostMapping(value="/post")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            userRepository.save(user);
            log.info("le user a bel et bien été rajouté");
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            log.warning("Le user n'a pas pu être rajouté");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

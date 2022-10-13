package com.bibliotheque.livre.controller;

import com.bibliotheque.livre.model.User;
import com.bibliotheque.livre.data.UserRepository;

import com.bibliotheque.livre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//@AllArgsConstructor
@Controller
@RequestMapping(value="compte") // This means URL's start with /demo (after Application path)

public class UserController {
    @Autowired
    private UserService userService;

    public UserController (UserService userService){
        this.userService = userService;
    }

    //Avoir la liste des livres dans un tableau
    @GetMapping (value = "/listeuser")
    public String getUser(Model model){

        log.info("direction page de liste users");
        model.addAttribute("user", userService.getAllUser());
        return "listeusers";
    }


    //Creer un nouveau user
    @GetMapping (value = "/nouveaucompte")
    public String createUserForm (Model model) {
        model.addAttribute("titre", "Creation d'un Compte utilisateur");
        //creer objet user pour contenir les donnees user du formulaire
        User user = new User ();
        model.addAttribute("User", user);
        return "creationcompte";
    }
    @PostMapping(value="/utilisateurs")
    public String saveUser (@ModelAttribute ("User") User user){
        userService.saveUser(user);
        System.out.println(userService.saveUser(user));
        return "redirect:/compte/listeuser";
    }


    //Modifier les users
    @GetMapping(value = "/user/edit/{id}")

    public String editUserForm(@PathVariable Long id, Model model){

        model.addAttribute("titremodif", "Modifier le compte");
        model.addAttribute("user", userService.getUserById(id));
        return "modifUser";
    }

    @PostMapping(value = "/modifuser/{id}")
    public String updateUser(@PathVariable long id,
                             @ModelAttribute("user") User user,
                              Model model){

        log.info("direction page de modification de l'utilisateur");
        //recevoir User de la base de donnée
        User existingUser = userService.getUserById(id);
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setEmail(user.getEmail());
        existingUser.setMdp(user.getMdp());

        //sauvegarder l'objet user
        userService.updateUser(existingUser);
        System.out.println(userService.updateUser(existingUser));
        return "redirect:/compte/listeuser";
    }

    //Supprimer un utilisateur
    @GetMapping(value = "/deleteuser/{id}")
    public String deleteUser(@PathVariable long id){
        userService.deleteUserById(id);
        return "redirect:/compte/listeuser";
    }


}

package com.bibliotheque.livre.controller;

import com.bibliotheque.livre.model.User;

import com.bibliotheque.livre.service.LivreService;
import com.bibliotheque.livre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.java.Log;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Log
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@AllArgsConstructor
@Controller
@RequestMapping(value="compte") // This means URL's start with /demo (after Application path)

public class UserController {

    private UserService userService;
    private LivreService livreService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, LivreService livreService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.livreService = livreService;
        this.passwordEncoder = passwordEncoder;
    }




    //Creer un nouveau user

    @GetMapping (value = "/nouveaucompte")
    public String createUserForm (Model model) {
        model.addAttribute("titre", "Creation de votre compte");
        //creer objet user pour contenir les donnees user du formulaire
        User user = new User ();
        model.addAttribute("User", user);
        return "creationcompte";
    }

    @PostMapping(value="/utilisateurs")
    public String saveUser (@ModelAttribute ("User") User user){
        //encoder le mot de passe
        user.setMdp(passwordEncoder.encode(user.getMdp()));
        userService.saveUser(user);
        System.out.println(userService.saveUser(user));
        return "redirect:/compte/liste";
    }


    //Récupérer l'utilisateur connecté

    @GetMapping (value="/test")
    public String getUser (Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        model.addAttribute("user", userDetails);
        return "test";
    }

    /* @GetMapping(value = "/user/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model){

        model.addAttribute("titremodif", "Modifier le compte");
        model.addAttribute("user", userService.getUserById(id));
        return "usermodif";
    } */


    //Modifier les users
    @PostMapping (value = "/modifuser")
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


    //Avoir la liste des livres dans un tableau
    @GetMapping (value = "/liste")
    public String getLivre(Model model){
        model.addAttribute("something", "Pour connaitre les livres");
        log.info("direction page de liste de livre");
        model.addAttribute("livre", livreService.getAllLivres());
        return "adminaccueil";
    }


    //recevoir les données d'un user
    @GetMapping(value = "/user/{id}")

    public String userForm(@PathVariable Long id, Model model){

        model.addAttribute("titremodif", "Modifier le compte");
        model.addAttribute("user", userService.getUserById(id));
        return "adminaccueil";
    }


}

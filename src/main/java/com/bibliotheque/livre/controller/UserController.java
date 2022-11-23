package com.bibliotheque.livre.controller;

import com.bibliotheque.livre.form.PretForm;
import com.bibliotheque.livre.model.Pret;
import com.bibliotheque.livre.model.User;

import com.bibliotheque.livre.service.LivreService;
import com.bibliotheque.livre.service.PretService;
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

import javax.validation.Valid;

@Log
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@AllArgsConstructor
@Controller
@RequestMapping(value="compte") // This means URL's start with /demo (after Application path)

public class UserController {

    private UserService userService;
    private LivreService livreService;

    private PasswordEncoder passwordEncoder;

    private PretService pretService;

    @Autowired
    public UserController(UserService userService, LivreService livreService, PasswordEncoder passwordEncoder, PretService pretService){
        this.userService = userService;
        this.livreService = livreService;
        this.passwordEncoder = passwordEncoder;
        this.pretService = pretService;
    }

    //Avoir la liste des livres dans un tableau
    @GetMapping (value = "/liste")
    public String getLivre(Model model){
        model.addAttribute("something", "Notre liste de livre");
        log.info("direction page de liste de livre");
        model.addAttribute("livre", livreService.getAllLivres());
        return "adminaccueil";
    }

    //Avoir la liste de mes prets
    @GetMapping (value = "/mesprets")
    public String getPret(Model model){
        model.addAttribute("titre", "Mes prêts");
        User user = userService.getCurrentUser();

        model.addAttribute("pret", pretService.getUserPret(user.getId()));
        return "userlistepret";
    }

    //Ajouter les donnees d'un nouvel user
    @GetMapping (value = "/nouveaucompte")
    public String createUserForm (Model model) {
        model.addAttribute("titre", "Creation de votre compte");
        //creer objet user pour contenir les donnees user du formulaire
        User user = new User ();
        model.addAttribute("User", user);
        return "creationcompte";
    }

    //Creer un nouveau user
    @PostMapping(value = "/utilisateurs")
    public String saveUser (@ModelAttribute ("User") User user){
        //encoder le mot de passe
        user.setMdp(passwordEncoder.encode(user.getMdp()));
        userService.saveUser(user);
        return "redirect:/compte/liste";
    }


    //Récupérer l'utilisateur connecté
    @GetMapping(value = "/viewutilisateur")
    public String viewPret (Model model){
        model.addAttribute("titremodif", "Modification du profil");
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "usermodif";
    }

    //Modifier mon profil
    @PostMapping (value = "/modifuser")
    public String updateUser(
                             @ModelAttribute("user") User user,
                              Model model){

        //recevoir User de la base de donnée
        User existingUser = userService.getCurrentUser();
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setEmail(user.getEmail());
        existingUser.setMdp(passwordEncoder.encode(user.getMdp()));

        //sauvegarder l'objet user
        userService.updateUser(existingUser);
        return "redirect:/compte/liste";
    }

    //recevoir les données d'un user
    @GetMapping(value = "/user/{id}")
    public String userForm(@PathVariable Long id, Model model){

        model.addAttribute("titremodif", "Modifier le compte");
        model.addAttribute("user", userService.getUserById(id));
        return "adminaccueil";
    }

    //Modifier un user par l'admin
    @PostMapping (value = "/modiftheuser")
    public String updateUsers(@PathVariable Long id,
            @ModelAttribute("user") User user,
            Model model){

        //recevoir User de la base de donnée
        User existingUser = userService.getUserById(id);
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setEmail(user.getEmail());
        existingUser.setMdp(passwordEncoder.encode(user.getMdp()));

        //sauvegarder l'objet user
        userService.updateUser(existingUser);
        return "redirect:/compte/liste";
    }

    //Supprimer un utilisateur
    @GetMapping(value = "/deleteuser/{id}")
    public String deleteUser(@PathVariable long id){
        userService.deleteUserById(id);
        return "redirect:/admin/listeuser";
    }

}

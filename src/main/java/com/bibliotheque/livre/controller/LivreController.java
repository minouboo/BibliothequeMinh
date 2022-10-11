package com.bibliotheque.livre.controller;

import com.bibliotheque.livre.model.Livre;
import com.bibliotheque.livre.data.LivreRepository;

import com.bibliotheque.livre.service.LivreService;
import com.bibliotheque.livre.service.implement.LivreServiceImplement;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.java.Log;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Log
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
/*@AllArgsConstructor*/
@Controller
@RequestMapping(value="bibliothequepc") // This means URL's start with /demo (after Application path)

public class LivreController {


    @Autowired
    private LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    //Avoir la liste des livres dans un tableau
    @GetMapping (value = "/liste")
    public String getLivre(Model model){
        model.addAttribute("something", "Pour connaitre les livres");
        log.info("direction page de liste de livre");
        model.addAttribute("livre", livreService.getAllLivres());
        return "parcourirbiblio";
    }

    //Creer un nouveau livre
    @GetMapping (value = "/newlivre")
    public String createLivreForm (Model model){
        model.addAttribute("something", "enregistrer un livre");
        //creer objet livre pour contenir les donnees livres du formulaire
        Livre livre = new Livre();
        model.addAttribute("livre", livre);
        return "creationlivre";
    }

    @PostMapping (value = "/livres")
    public String saveLivre(@ModelAttribute("livre") Livre livre) {
        livreService.saveLivre(livre);
        return"redirect:/bibliothequepc/liste";
    }


    //Modifier les livres
    @GetMapping( value = "/livres/edit/{id}")

    public String editLivreForm(@PathVariable Long id, Model model){

        model.addAttribute("something", "Modifier un livre");

        model.addAttribute("livre",livreService.getLivreById(id));
        return "modifLivre";
    }

    @PostMapping(value = "/livres/{id}")
    public String updateLivre(@PathVariable long id,
                              @ModelAttribute("livre") Livre livre,
                              Model model) {

        log.info("direction page modification de livre");
        //recevoir livre de la base de donnee
        Livre existingLivre = livreService.getLivreById(id);
        existingLivre.setIsbn(livre.getIsbn());
        existingLivre.setTitre(livre.getTitre());
        existingLivre.setAuteur(livre.getAuteur());
        existingLivre.setDescription(livre.getDescription());

        /* existingLivre.setEditeur_id(livre.getEditeur_id());
        existingLivre.setGenre(livre.getGenre());
        existingLivre.setLangue(livre.getLangue()); */

        //sauvegarder l'objet livre
        livreService.updateLivre(existingLivre);
        System.out.println(livreService.updateLivre(existingLivre));
        return "redirect:/bibliothequepc/liste";

    }

    // supprimer le livre

    @GetMapping(value = "/livres/{id}")
    public String deleteLivre(@PathVariable long id){
        livreService.deleteLivreById(id);
        return "redirect:/bibliothequepc/liste";
    }

    /*
    @GetMapping(value="/get")
    public ResponseEntity<List<Livre>> getAllLivre(){
        List<Livre>livres = new ArrayList<>();
        System.out.println(livres);
        livreRepository.findAll().forEach(livres::add);
        log.info("la liste des livres est bel et bien renvoye");
        return new ResponseEntity<>(livres, HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping(value="/get/{id}")
    public String getLivreById(@PathVariable(value="id")long livreId, Model model){
        log.info("Méthode de gestion de requête HTTP GET pour trouver un Livre par son id");
        Livre livre = livreRepository.findById(livreId)
                .orElse(null);
        model.addAttribute("something", "this is from Person Controller");

        return "test1";
    }
            */


}

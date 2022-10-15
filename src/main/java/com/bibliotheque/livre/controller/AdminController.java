package com.bibliotheque.livre.controller;

import com.bibliotheque.livre.form.LivreForm;
import com.bibliotheque.livre.model.*;

import com.bibliotheque.livre.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
/*@AllArgsConstructor*/
@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping(value="admin") // This means URL's start with /demo (after Application path)

public class AdminController {



    private LivreService livreService;
    private LangueService langueService;
    private AuteurService auteurService;
    private EditeurService editeurService;
    private GenreService genreService;
    private UserService userService;


    @Autowired
    public AdminController(LivreService livreService, LangueService langueService, AuteurService auteurService, EditeurService editeurService, GenreService genreService, UserService userService) {
        this.livreService = livreService;
        this.langueService = langueService;
        this.auteurService = auteurService;
        this.editeurService = editeurService;
        this.genreService = genreService;
        this.userService = userService;
    }


    //Avoir la liste des livres dans un tableau
    @GetMapping (value = "/liste")
    public String getLivre(Model model){
        model.addAttribute("something", "Pour connaitre les livres");
        log.info("direction page de liste de livre");
        model.addAttribute("livre", livreService.getAllLivres());
        return "adminaccueil";
    }

    //Avoir la liste des users dans un tableau
    @GetMapping(value = "/listeuser")
    public String getUser(Model model){

        log.info("direction page de liste users");
        model.addAttribute("user", userService.getAllUser());
        return "adminlisteusers";
    }


    //entree les données d'un nouveau livre
    @GetMapping (value = "/newlivre")
    public String createLivreForm (Model model){
        model.addAttribute("titre", "Ajouter un livre à la Bibliotheque");
        //ajout de l'attribut en liste
        model.addAttribute("langues", langueService.getAllLangues());
        model.addAttribute("editeur", editeurService.getAllEditeur());
        model.addAttribute("genre", genreService.getAllGenre());
        //ajout de l'attribut auteur en liste
        //model.addAttribute("auteur", auteurService.getAllAuteur());
        //creer objet livre pour contenir les donnees livres du formulaire
        LivreForm livre = new LivreForm();
        model.addAttribute("livre", livre);
        return "admincreationlivre";
    }

    //Enregistrer le nouveau livre

   /* @PostMapping (value = "/livres")
    public String saveLivre(@ModelAttribute("livre") Livre livre) {
        livreService.saveLivre(livre);
        System.out.println(livreService.saveLivre(livre));
        return"redirect:/liste";
    } */

    @PostMapping (value = "/livres")
    public String saveLivre( @Valid @ModelAttribute("livre") LivreForm livre) {
        Livre l = new Livre();
        l.setIsbn(livre.getIsbn());
        l.setTitre(livre.getTitre());
        Langue langue= langueService.findById(livre.getLangueId());

        l.setLangue(langue);
        livreService.saveLivre(l);
        return"redirect:/admin/liste";
    }


    //Modifier les livres
    @GetMapping( value = "/livres/edit/{id}")

    public String editLivreForm(@PathVariable Long id, Model model){

        model.addAttribute("something", "Modifier un livre");

        model.addAttribute("livre",livreService.getLivreById(id));
        return "adminmodifLivre";
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
        existingLivre.setDescription(livre.getDescription());
        existingLivre.setGenre(livre.getGenre());
        existingLivre.setLangue(livre.getLangue());
        /*
        existingLivre.setAuteur(livre.getAuteur());
        existingLivre.setEditeur_id(livre.getEditeur_id()); */

        //sauvegarder l'objet livre

        livreService.updateLivre(existingLivre);
        System.out.println(livreService.updateLivre(existingLivre));
        return "redirect:/adminlivre/liste";

    }


    // supprimer le livre
    @GetMapping(value = "/livres/{id}")
    public String deleteLivre(@PathVariable long id){
        livreService.deleteLivreById(id);
        return "redirect:/adminlivre/liste";
    }


    //Creer une nouvelle langue
    @GetMapping (value = "/newlangue")
    public String createLangueForm (Model model){
        model.addAttribute("titre", "Ajouter une nouvelle langue");
        //creer objet livre pour contenir les donnees livres du formulaire
        Langue langue = new Langue();
        model.addAttribute("langue", langue);
        return "admincreationlangue";
    }

    @PostMapping (value = "/langues")
    public String saveLangue(@ModelAttribute("langue") Langue langue) {
        langueService.saveLangue(langue);
        return"redirect:/admin/liste";
    }

    //Creer une nouvelle langue
    @GetMapping (value = "/newediteur")
    public String createEditeurForm (Model model){
        model.addAttribute("titre", "Ajouter un nouveau éditeur");
        //creer objet livre pour contenir les donnees livres du formulaire
        Editeur editeur = new Editeur();
        model.addAttribute("editeur", editeur);
        return "admincreationediteur";
    }

    @PostMapping (value = "/editeurs")
    public String saveEditeur(@ModelAttribute("editeur") Editeur editeur) {
        editeurService.saveEditeur(editeur);
        return"redirect:/admin/liste";
    }

    //Creer un nouveau genre
    @GetMapping (value = "/newgenre")
    public String createGenreForm (Model model){
        model.addAttribute("titre", "Ajouter un nouveau genre");
        //creer objet livre pour contenir les donnees livres du formulaire
        Genre genre = new Genre();
        model.addAttribute("genre", genre);
        return "admincreationgenre";
    }

    @PostMapping (value = "/genres")
    public String saveGenre(@ModelAttribute("genre") Genre genre) {
        genreService.saveGenre(genre);
        return"redirect:/admin/liste";
    }

    //Creer un nouvel auteur
    @GetMapping (value = "/newauteur")
    public String createAuteurForm (Model model){
        model.addAttribute("titre", "Ajouter un nouvel auteur");
        //creer objet livre pour contenir les donnees livres du formulaire
        Auteur auteur = new Auteur();
        model.addAttribute("auteur", auteur);
        return "admincreationauteur";
    }

    @PostMapping (value = "/auteurs")
    public String saveAuteur(@ModelAttribute("auteur") Auteur auteur) {
        auteurService.saveAuteur(auteur);
        return"redirect:/admin/liste";
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

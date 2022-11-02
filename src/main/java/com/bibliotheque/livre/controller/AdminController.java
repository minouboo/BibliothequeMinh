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


    //Avoir la liste des users dans un tableau
    @GetMapping(value = "/listeuser")
    public String getUser(Model model){

        log.info("direction page de liste users");
        model.addAttribute("user", userService.getAllUser());
        return "adminlisteusers";
    }

    //Avoir la liste des livres dans un tableau
    @GetMapping (value = "/liste")
    public String getLivre(Model model){
        model.addAttribute("something", "Notre liste de livre");
        model.addAttribute("livre", livreService.getAllLivres());
        return "adminaccueil";
    }


    //entree les données d'un nouveau livre
    @GetMapping (value = "/newlivre")
    public String createLivreForm (Model model){
        model.addAttribute("titre", "Ajouter un livre à la Bibliotheque");
        //ajout de l'attribut en liste
        model.addAttribute("langues", langueService.getAllLangues());
        model.addAttribute("editeurs", editeurService.getAllEditeurs());
        model.addAttribute("genres", genreService.getAllGenre());
        //ajout de l'attribut auteur en liste
        model.addAttribute("auteurs", auteurService.getAllAuteur());
        //creer objet livre pour contenir les donnees livres du formulaire
        LivreForm livre = new LivreForm();
        model.addAttribute("livre", livre);
        return "admincreationlivre";
    }


    //Enregistrer le nouveau livre
    @PostMapping (value = "/livres")
    public String saveLivre( @Valid @ModelAttribute("livre") LivreForm livre) {
        Livre l = new Livre();
        l.setIsbn(livre.getIsbn());
        l.setTitre(livre.getTitre());
        l.setDateDePublication(livre.getDateDePublication());
        Langue langue = langueService.findLangueById(livre.getLangueId());
        Editeur editeur = editeurService.findEditeurById(livre.getEditeurId());
        Genre genre = genreService.findGenreById(livre.getGenreId());
        for (Long id:livre.getAuteursId()
             ) {
            Auteur auteur = auteurService.findAuteurById(id);
            if (auteur != null){
                l.getAuteurs().add(auteur);
            }
        };

        l.setLangue(langue);
        l.setEditeur(editeur);
        l.setGenre(genre);

        livreService.saveLivre(l);
        return"redirect:/admin/liste";
    }

    //Modifier un livre
    @GetMapping( value = "/livres/edit/{id}")
    public String editLivreForm(@PathVariable Long id, Model model){

        model.addAttribute("titre", "Modifier un livre");

        //ajout de l'attribut en liste
        model.addAttribute("langues", langueService.getAllLangues());
        model.addAttribute("editeurs", editeurService.getAllEditeurs());
        model.addAttribute("genres", genreService.getAllGenre());
        //ajout de l'attribut auteur en liste
        model.addAttribute("auteurs", auteurService.getAllAuteur());

        //LivreForm livremodif = new LivreForm();

        model.addAttribute("livre", livreService.getLivreById(id));


        return "adminmodifLivre";
    }

    @PostMapping(value = "/livres/{id}")
    public String updateLivre(@PathVariable Long id, @ModelAttribute("livre") Livre livre) {
        //Livre livreUpdated = new Livre();
        //recevoir livre de la base de donnee
        //livreUpdated.setIsbn(livre.getIsbn());
        //livreUpdated.setTitre(livre.getTitre());
        Livre existingLivre = livreService.getLivreById(id);
        existingLivre.setIsbn(livre.getIsbn());
        existingLivre.setTitre(livre.getTitre());
        existingLivre.setLangue(livre.getLangue());
        existingLivre.setGenre(livre.getGenre());
        existingLivre.setEditeur(livre.getEditeur());
        existingLivre.setAuteurs(livre.getAuteurs());
        //existingLivre.setDescription(livre.getDescription());
        //existingLivre.setAuteur(livre.getAuteur());

        //Langue langue = langueService.findLangueById(livre.getLangueId());
        //Genre genre = genreService.findGenreById(livre.getGenreId());

        //Modifier l'objet livre
        livreService.updateLivre(existingLivre);

        return "redirect:/admin/liste";
    }


    // supprimer le livre
    @GetMapping(value = "/livres/{id}")
    public String deleteLivre(@PathVariable Long id){
        livreService.deleteLivreById(id);
        return "redirect:/admin/liste";
    }

    // Pret de livre
    @GetMapping (value = "pret/{id}")
    public String pretLivre (@PathVariable Long id, Model model){

        model.addAttribute("livre", livreService.getLivreById(id));
        return "userpret";
    }



    //Ajouter une nouvelle langue
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

    //Ajouter un éditeur
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

    //Ajouter un nouveau genre
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

    //Ajouter un nouvel auteur
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

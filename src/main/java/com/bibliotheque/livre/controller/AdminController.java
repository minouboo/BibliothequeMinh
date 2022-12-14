package com.bibliotheque.livre.controller;

import com.bibliotheque.livre.data.DescriptionRepository;
import com.bibliotheque.livre.data.LivreRepository;
import com.bibliotheque.livre.form.ExemplaireForm;
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
//@AllArgsConstructor
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
    private ExemplaireService exemplaireService;
    private LivreRepository livreRepository;

    private DescriptionRepository descriptionRepository;


    @Autowired
    public AdminController(LivreService livreService, LangueService langueService, AuteurService auteurService, EditeurService editeurService, GenreService genreService, UserService userService, ExemplaireService exemplaireService, LivreRepository livreRepository, DescriptionRepository descriptionRepository) {
        this.livreService = livreService;
        this.langueService = langueService;
        this.auteurService = auteurService;
        this.editeurService = editeurService;
        this.genreService = genreService;
        this.userService = userService;
        this.exemplaireService = exemplaireService;
        this.livreRepository = livreRepository;
        this.descriptionRepository = descriptionRepository;
    }


    //Avoir la liste des users dans un tableau
    @GetMapping(value = "/listeuser")
    public String getUser(Model model){
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

    //entree les donn??es d'un nouveau livre
    @GetMapping (value = "/newlivre")
    public String createLivreForm (Model model){
        model.addAttribute("titre", "Ajouter un livre ?? la Bibliotheque");

        //Selection de l'attribut en liste pour le livre
        model.addAttribute("langues", langueService.getAllLangues());
        model.addAttribute("editeurs", editeurService.getAllEditeurs());
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("auteurs", auteurService.getAllAuteur());

        //creer objet livre pour contenir les donnees livres du formulaire
        LivreForm livre = new LivreForm();
        model.addAttribute("livre", livre);

        //Ajout des attibuts
        Langue langue = new Langue();
        model.addAttribute("langue", langue);

        Editeur editeur = new Editeur();
        model.addAttribute("editeur", editeur);

        Genre genre = new Genre();
        model.addAttribute("genre", genre);

        Auteur auteur = new Auteur();
        model.addAttribute("auteur", auteur);

        return "admincreationlivre";
    }


    //Enregistrer le nouveau livre
    @PostMapping (value = "/livres")
    public String saveLivre( @Valid @ModelAttribute("livre") LivreForm livre) {
        Livre l = new Livre();

        l.setIsbn(livre.getIsbn());
        l.setTitre(livre.getTitre());

        Langue langue = langueService.findLangueById(livre.getLangueId());
        l.setLangue(langue);

        Editeur editeur = editeurService.findEditeurById(livre.getEditeurId());
        l.setEditeur(editeur);

        Genre genre = genreService.findGenreById(livre.getGenreId());
        l.setGenre(genre);

        for (Long id:livre.getAuteursId()
             ) {
            Auteur auteur = auteurService.findAuteurById(id);
            if (auteur != null){
                l.getAuteurs().add(auteur);
            }
        };

        l.setDateDePublication(livre.getDateDePublication());

        //Paragraphe p = new Paragraphe();
        //p.setTexte(livre.getDescription());

        Description d = new Description();
        d.setTitre(livre.getTitre());
        livreService.convertToParagraphes(livre.getParagraphe(),d);
        d = descriptionRepository.save(d);

        l.setDescription(d);
        descriptionRepository.save(d);

        livreService.saveLivre(l);

        return"redirect:/admin/liste";
    }

    //Modifier un livre
    @GetMapping( value = "/livres/edit/{id}")
    public String editLivreForm(@PathVariable Long id, Model model){

        model.addAttribute("titre", "Modifier un livre");

        //donn??es du livre ?? modifier
        model.addAttribute("langues", langueService.getAllLangues());
        model.addAttribute("editeurs", editeurService.getAllEditeurs());
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("auteurs", auteurService.getAllAuteur());
        model.addAttribute("livre", livreService.getLivreById(id));

        //donn??es de l'exemplaire ?? ajouter
        model.addAttribute("titreajout", "Ajouter un exemplaire");
        ExemplaireForm exemplaire = new ExemplaireForm();
        model.addAttribute("exemplaire", exemplaire);

        //Lister les exemplaires d'un livre
        model.addAttribute("titreexemplaireexistant", "Exemplaire existant");
        model.addAttribute("listeexemplaire", exemplaireService.getExemplaireDispoLivre(id));

        //suppression d'un exemplaire
        model.addAttribute("titresup", "Supprimer un exemplaire");
        model.addAttribute("exemplaires", exemplaireService.getAllExemplaire());

        return "adminmodifLivre";
    }

    //Enregistrer le livre modifi??
    @PostMapping(value = "/livres/{id}")
    public String updateLivre(@PathVariable Long id, @ModelAttribute("livre") Livre livre) {

        Livre existingLivre = livreService.getLivreById(id);
        existingLivre.setIsbn(livre.getIsbn());
        existingLivre.setTitre(livre.getTitre());
        existingLivre.setLangue(livre.getLangue());
        existingLivre.setGenre(livre.getGenre());
        existingLivre.setEditeur(livre.getEditeur());
        existingLivre.setAuteurs(livre.getAuteurs());
        existingLivre.setDateDePublication(livre.getDateDePublication());

        //Modifier l'objet livre
        livreService.updateLivre(existingLivre);

        return "redirect:/admin/liste";
    }

    //Ajouter un exemplaire
    @PostMapping(value = "/newexemplaire")
    public String addExemplaire (@Valid @ModelAttribute("exemplaire") ExemplaireForm exemplaire){
        Exemplaire e = new Exemplaire();
        Livre livre = livreService.findLivreById(exemplaire.getLivreId());
        e.setLivre(livre);
        e.setCodeBarre(exemplaire.getCodeBarre());
        exemplaireService.saveExemplaire(e);
        return "redirect:/admin/liste" ;
    }

    // supprimer un exemplaire
    @GetMapping(value = "/exemplaire/{id}")
    public String deleteExemplaire(@PathVariable Long id){
        exemplaireService.deleteExemplaireById(id);
        return "redirect:/admin/liste";
    }

    // supprimer le livre
    @GetMapping(value = "/livres/{id}")
    public String deleteLivre(@PathVariable Long id){
        livreService.deleteLivreById(id);
        return "redirect:/admin/liste";
    }

    //Ajouter une nouvelle langue
    @PostMapping (value = "/langues")
    public String saveLangue(@ModelAttribute("langue") Langue langue) {
        langueService.saveLangue(langue);
        return"redirect:/admin/newlivre";
    }

    //Ajouter un ??diteur
    @PostMapping (value = "/editeurs")
    public String saveEditeur(@ModelAttribute("editeur") Editeur editeur) {
        editeurService.saveEditeur(editeur);
        return"redirect:/admin/newlivre";
    }

    //Ajouter un nouveau genre
    @PostMapping (value = "/genres")
    public String saveGenre(@ModelAttribute("genre") Genre genre) {
        genreService.saveGenre(genre);
        return"redirect:/admin/newlivre";
    }

    //Ajouter un nouvel auteur
    @PostMapping (value = "/auteurs")
    public String saveAuteur(@ModelAttribute("auteur") Auteur auteur) {
        auteurService.saveAuteur(auteur);
        return"redirect:/admin/newlivre";
    }

    //Avoir la liste des livres SF dans un tableau
    @GetMapping (value = "/listeSF")
    public String getLivreSF(Model model){
        model.addAttribute("something", "Notre liste de livre");
        // model.addAttribute("livre", livreRepository.findLivresBySF());
        return "adminaccueil";
    }


}

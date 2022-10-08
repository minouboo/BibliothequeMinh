package com.bibliotheque.livre.controller;

import com.bibliotheque.livre.model.Livre;
import com.bibliotheque.livre.data.LivreRepository;

import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(value="api/livre") // This means URL's start with /demo (after Application path)

public class LivreController {

    LivreRepository livreRepository;

    @GetMapping(value="/get")
    public ResponseEntity<List<Livre>> getAllLivre(){
        List<Livre>livres = new ArrayList<>();
        livreRepository.findAll().forEach(livres::add);
        log.info("la liste des livres est bel et bien renvoye");
        return new ResponseEntity<>(livres, HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping(value="/get/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable(value="id")long livreId){
        log.info("Méthode de gestion de requête HTTP GET pour trouver un Livre par son id");
        Livre livre = livreRepository.findById(livreId).orElse(null);
        return new ResponseEntity<>(livre, HttpStatus.OK);
    }




}

package com.bibliotheque.livre.controller;

import com.bibliotheque.livre.model.Livre;
import com.bibliotheque.livre.model.Pret;
import com.bibliotheque.livre.data.PretRepository;

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
@RequestMapping(value="api/pret") // This means URL's start with /demo (after Application path)

public class PretController {

    PretRepository pretRepository;

    @GetMapping(value="/get")
    public ResponseEntity<List<Pret>> getAllLivre(){
        List<Pret>prets = new ArrayList<>();
        pretRepository.findAll().forEach(prets::add);
        log.info("la liste des pret est bel et bien renvoye");
        return new ResponseEntity<>(prets, HttpStatus.OK);
    }
}

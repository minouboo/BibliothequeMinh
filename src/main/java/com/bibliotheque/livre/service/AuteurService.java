package com.bibliotheque.livre.service;

import com.bibliotheque.livre.model.Auteur;

import java.util.List;

public interface AuteurService {

    List<Auteur> getAllAuteur();

    Auteur saveAuteur (Auteur auteur);

    Auteur findAuteurById(Long id);

}

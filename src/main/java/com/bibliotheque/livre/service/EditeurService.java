package com.bibliotheque.livre.service;

import com.bibliotheque.livre.model.Editeur;

import java.util.List;

public interface EditeurService {

    List<Editeur>getAllEditeurs();

    Editeur saveEditeur(Editeur editeur);

    Editeur findEditeurById(Long id);

}

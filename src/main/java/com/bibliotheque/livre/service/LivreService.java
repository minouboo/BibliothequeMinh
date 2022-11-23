package com.bibliotheque.livre.service;

import java.util.List;

import com.bibliotheque.livre.model.Description;
import com.bibliotheque.livre.model.Livre;
import com.bibliotheque.livre.model.Paragraphe;

public interface LivreService {

    //pour une liste de livre
    List<Livre> getAllLivres();

    //pour enregistrer un livre
    Livre saveLivre(Livre livre);

    //pour recuperer un livre avec son id
    Livre getLivreById (Long id);

    //pour modifier un livre
    Livre updateLivre(Livre livre);

    //pour supprimer un livre
    void deleteLivreById (Long id);

    Livre findLivreById (Long id);

    void convertToParagraphes(String Text, Description description);



}

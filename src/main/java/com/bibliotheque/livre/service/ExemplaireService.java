package com.bibliotheque.livre.service;

import com.bibliotheque.livre.model.Exemplaire;

import java.util.List;


public interface ExemplaireService {

    Exemplaire saveExemplaire (Exemplaire exemplaire);

    List<Exemplaire> getAllExemplaire ();

    Exemplaire findExemplaireById (Long id);

    void deleteExemplaireById (Long id);

    List<Exemplaire> getExemplaireDispoLivre (Long id);

}

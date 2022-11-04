package com.bibliotheque.livre.service;


import com.bibliotheque.livre.model.Pret;

import java.util.List;

public interface PretService {

    //pour une liste de Pret
    List<Pret> getAllPret();

    //pour enregistrer un pret
    Pret savePret(Pret pret);

    //pour recuperer un Pret avec son id
    Pret getPretById (Long id);

    //pour modifier un Pret
    Pret updatePret (Pret pret);

    //pour supprimer un Pret
    void deletePretById (long id);

    //List<Pret> getUserPret(Long userId);



}

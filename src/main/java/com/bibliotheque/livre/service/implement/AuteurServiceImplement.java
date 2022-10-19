package com.bibliotheque.livre.service.implement;

import com.bibliotheque.livre.data.AuteurRepository;
import com.bibliotheque.livre.model.Auteur;
import com.bibliotheque.livre.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuteurServiceImplement implements AuteurService {

    @Autowired
    private AuteurRepository auteurRepository;

    @Override
    public List<Auteur> getAllAuteur() {
        return auteurRepository.findAll();
    }

    @Override
    public Auteur saveAuteur(Auteur auteur) {
        return auteurRepository.save(auteur);
    }

    @Override
    public Auteur findAuteurById(Long id) {
        return auteurRepository.findById(id).orElse(null);
    }
}

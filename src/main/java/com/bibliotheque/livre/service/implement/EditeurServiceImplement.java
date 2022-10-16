package com.bibliotheque.livre.service.implement;

import com.bibliotheque.livre.data.EditeurRepository;
import com.bibliotheque.livre.model.Editeur;
import com.bibliotheque.livre.service.EditeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditeurServiceImplement implements EditeurService {

    @Autowired
    private EditeurRepository editeurRepository;

    @Override
    public List<Editeur> getAllEditeurs() {
        return editeurRepository.findAll();
    }

    @Override
    public Editeur saveEditeur(Editeur editeur) {
        return editeurRepository.save(editeur);
    }

    @Override
    public Editeur findEditeurById(Long id) {
        return editeurRepository.findById(id).orElse(null);
    }
}

package com.bibliotheque.livre.service.implement;

import com.bibliotheque.livre.data.ExemplaireRepository;
import com.bibliotheque.livre.model.Exemplaire;
import com.bibliotheque.livre.service.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExemplaireServiceImplement implements ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Override
    public Exemplaire saveExemplaire (Exemplaire exemplaire){
        return exemplaireRepository.save(exemplaire);
    }




}

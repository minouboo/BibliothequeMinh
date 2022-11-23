package com.bibliotheque.livre.service.implement;

import com.bibliotheque.livre.data.ExemplaireRepository;
import com.bibliotheque.livre.model.Exemplaire;
import com.bibliotheque.livre.service.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExemplaireServiceImplement implements ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Override
    public Exemplaire saveExemplaire (Exemplaire exemplaire){
        return exemplaireRepository.save(exemplaire);
    }

    @Override
    public List<Exemplaire> getAllExemplaire() {
        return exemplaireRepository.findAll();
    }

    @Override
    public Exemplaire findExemplaireById(Long id) {
        return exemplaireRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteExemplaireById(Long id) {
        exemplaireRepository.deleteById(id);
    }

    @Override
    public List<Exemplaire> getExemplaireDispoLivre(Long livreid) {
        return exemplaireRepository.getExemplaireDispoLivre(livreid);
    }


}

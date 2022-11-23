package com.bibliotheque.livre.service.implement;

import com.bibliotheque.livre.data.PretRepository;
import com.bibliotheque.livre.model.Pret;
import com.bibliotheque.livre.service.PretService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PretServiceImplement implements PretService {

    private PretRepository pretRepository;

    public PretServiceImplement (PretRepository pretRepository){
        super ();
        this.pretRepository = pretRepository;
    }

    //methode pour avoir une liste de Pret
    @Override
    public List<Pret> getAllPret (){
        return pretRepository.findAll();
    }

    // methode pour sauvegarder un Pret
    @Override
    public Pret savePret(Pret pret) {
        return pretRepository.save(pret);
    }

    @Override
    public Pret getPretById(Long id) {

        return pretRepository.findById(id).get();
    }

    @Override
    public Pret updatePret(Pret pret) {

        return pretRepository.save(pret);
    }

    @Override
    public void deletePretById(long id) {

    }


    @Override
    public List<Pret> getUserPret(Long userId) {
        return pretRepository.getUserPret(userId);
    }




}

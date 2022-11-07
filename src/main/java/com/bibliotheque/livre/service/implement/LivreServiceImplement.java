package com.bibliotheque.livre.service.implement;

import com.bibliotheque.livre.data.LivreRepository;
import com.bibliotheque.livre.model.Livre;
import com.bibliotheque.livre.service.LivreService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreServiceImplement implements LivreService {

    private LivreRepository livreRepository;

    public LivreServiceImplement(LivreRepository livreRepository) {
        super();
        this.livreRepository = livreRepository;
    }

    //methode pour avoir une liste de livre
    @Override
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    // methode pour sauvegarder un livre
    @Override
    public Livre saveLivre (Livre livre) {
        return livreRepository.save(livre);
    }

    // methode pour recupere un livre avec Id
    @Override
    public Livre getLivreById(Long id) {

        return livreRepository.findById(id).get();
    }

    // methode pour modifier un livre
    @Override
    public Livre updateLivre(Livre livre) {

        return livreRepository.save(livre);
    }

    @Override
    public void deleteLivreById(long id) {
        livreRepository.deleteById(id);
    }

    @Override
    public Livre findLivreById (Long id) {return livreRepository.findById(id).orElse(null);}

    /*
    @Override
    public List<Livre> findLivreBySF (){
        return livreRepository.findLivresBySF();
    } */
}




package com.bibliotheque.livre.service.implement;

import com.bibliotheque.livre.data.LivreRepository;
import com.bibliotheque.livre.data.ParagrapheRepository;
import com.bibliotheque.livre.model.Description;
import com.bibliotheque.livre.model.Livre;
import com.bibliotheque.livre.model.Paragraphe;
import com.bibliotheque.livre.service.LivreService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreServiceImplement implements LivreService {

    private LivreRepository livreRepository;
    private ParagrapheRepository paragrapheRepository;

    public LivreServiceImplement(LivreRepository livreRepository, ParagrapheRepository paragrapheRepository) {
        super();
        this.livreRepository = livreRepository;
        this.paragrapheRepository = paragrapheRepository;
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
    public void deleteLivreById(Long id) {
        livreRepository.deleteById(id);
    }

    @Override
    public Livre findLivreById (Long id) {return livreRepository.findById(id).orElse(null);}

    @Override
    public void convertToParagraphes(String texte, Description description) {

        // Split le texte en tableau de paragraphe
        String [] tab = texte.split("\\r\\n");

        int i =0;

        for ( String line : tab){
            Paragraphe p = new Paragraphe();
            p.setTexte(line);
            p.setOrdre(i++);
            p.setDescription(description);
            //paragrapheRepository.save(p);
            description.getParagraphes().add(p);

        }




    }

}




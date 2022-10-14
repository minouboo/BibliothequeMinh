package com.bibliotheque.livre.service.implement;


import com.bibliotheque.livre.data.GenreRepository;
import com.bibliotheque.livre.model.Genre;
import com.bibliotheque.livre.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImplement implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }
}

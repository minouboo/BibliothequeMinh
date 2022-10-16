package com.bibliotheque.livre.service;

import com.bibliotheque.livre.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre>getAllGenre();

    Genre saveGenre(Genre genre);

    Genre findGenreById(Long id);

}

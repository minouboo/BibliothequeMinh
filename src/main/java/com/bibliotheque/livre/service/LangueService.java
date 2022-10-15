package com.bibliotheque.livre.service;

import com.bibliotheque.livre.model.Langue;
import com.bibliotheque.livre.model.Livre;

import java.util.List;

public interface LangueService {

    List<Langue> getAllLangues();

    Langue saveLangue(Langue langue);

    Langue findById(Long id);

}

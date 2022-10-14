package com.bibliotheque.livre.service.implement;

import com.bibliotheque.livre.data.LangueRepository;
import com.bibliotheque.livre.model.Langue;
import com.bibliotheque.livre.service.LangueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LangueServiceImplement implements LangueService {

    @Autowired
    private LangueRepository langueRepository;

    @Override
    public List<Langue> getAllLangues() {
        return langueRepository.findAll();
    }
}
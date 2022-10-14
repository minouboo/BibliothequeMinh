package com.bibliotheque.livre.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotheque.livre.data.LangueRepository;
import com.bibliotheque.livre.model.Langue;
import com.bibliotheque.livre.service.LangueService;

@Service
public class LangueServiceImplement implements LangueService {

    @Autowired
    private LangueRepository langueRepository;

    @Override
    public List<Langue> getAllLangues() {
        return langueRepository.findAll();
    }

    @Override
    public Langue saveLangue(Langue langue) {
        return langueRepository.save(langue);
    }

	@Override
	public Langue findById(Long id) {
		return  langueRepository.findById(id).orElse(null);
	}


}

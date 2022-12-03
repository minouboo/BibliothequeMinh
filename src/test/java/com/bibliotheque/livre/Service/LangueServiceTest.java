package com.bibliotheque.livre.Service;

import com.bibliotheque.livre.data.LangueRepository;
import com.bibliotheque.livre.model.Langue;
import com.bibliotheque.livre.service.LangueService;
import com.bibliotheque.livre.service.implement.LangueServiceImplement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LangueServiceTest {

    @Mock
    private LangueRepository langueRepository;


    @InjectMocks
    private LangueServiceImplement langueServiceImplement;


    @Test
    public void testGetLangue (){

        // Preparation du test
        Langue expectedLangue = new Langue();
        expectedLangue.setId(1L);

        //test de la methode
        //option.of = englobe null
        when (langueRepository.findById(1L)).thenReturn(Optional.of(expectedLangue));
        Langue langue = langueServiceImplement.findLangueById(1L);

        //Résultat attendu
        assertNotNull(langue);

    }

    @Test
    public void testSaveLangue (){

        // preparation du test
        Langue langueSaved = new Langue();
        langueSaved.setNom("Anglais");

        // methode a tester
        when (langueRepository.save(langueSaved)).thenReturn(langueSaved);
        langueSaved = langueServiceImplement.saveLangue(langueSaved);

        //resultat du test
        assertNotNull(langueSaved);

    }

    @Test
    public void testGetAllLangue (){


        Langue langue1 = new Langue();
        langue1.setNom("Français");
        Langue langue2 = new Langue();
        langue2.setNom("Anglais");
        List<Langue> langues = Arrays.asList(langue1,langue2);

        when (langueRepository.findAll()).thenReturn(langues);
        List<Langue> allLangues = langueServiceImplement.getAllLangues();

        assertNotNull(allLangues);
        assertEquals(2,allLangues.size());

    }





}

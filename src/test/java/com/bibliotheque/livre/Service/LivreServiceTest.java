package com.bibliotheque.livre.Service;

import com.bibliotheque.livre.data.LivreRepository;
import com.bibliotheque.livre.form.LivreForm;
import com.bibliotheque.livre.model.Description;
import com.bibliotheque.livre.model.Livre;
import com.bibliotheque.livre.model.Paragraphe;
import com.bibliotheque.livre.service.implement.LivreServiceImplement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class LivreServiceTest {

    @Mock
    private LivreRepository livrerepository;

    @InjectMocks
    private LivreServiceImplement livreServiceImplement;


    @Test
    public void convertToParagraphTest (){

        //Preparation du test
        String texte = "asdfa \r\n asfasf \r\n safasdffl";
        Description expectedDescription = new Description();
        expectedDescription.setTitre("fables");


        // methode à tester

       livreServiceImplement.convertToParagraphes(texte, expectedDescription);

       // Résultat attendu

        Assertions.assertNotNull(expectedDescription.getParagraphes());
        Assertions.assertEquals(3,expectedDescription.getParagraphes().size());


    }

    @Test
    public void convertToParagraphShouldAddNoParagraphTest () {

        //Preparation du test
        String texte = "";
        Description expectedDescription = new Description();
        expectedDescription.setTitre("fables");


        // methode à tester

        livreServiceImplement.convertToParagraphes(texte, expectedDescription);

        // Résultat attendu

        Assertions.assertNotNull(expectedDescription.getParagraphes());
        Assertions.assertEquals(0, expectedDescription.getParagraphes().size());
    }

    @Test
    public void convertToParagraphShouldAddNoParagraphTest2 () {

        //Preparation du test
        String texte = " ";
        Description expectedDescription = new Description();
        expectedDescription.setTitre("fables");


        // methode à tester

        livreServiceImplement.convertToParagraphes(texte, expectedDescription);

        // Résultat attendu

        Assertions.assertNotNull(expectedDescription.getParagraphes());
        Assertions.assertEquals(0, expectedDescription.getParagraphes().size());
    }

    @Test
    public void convertToParagraphShouldAddNoParagraphWithoutDescription () {

        //Preparation du test
        String texte = " ";
        Description expectedDescription = null;



        // methode à tester

        livreServiceImplement.convertToParagraphes(texte, expectedDescription);

        // Résultat attendu
        Assertions.assertNull(expectedDescription);


    }

}

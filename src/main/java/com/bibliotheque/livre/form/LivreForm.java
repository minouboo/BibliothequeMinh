package com.bibliotheque.livre.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bibliotheque.livre.model.Auteur;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class LivreForm {

    private static final long serialVersionUID = 1L;

    @NotNull (message = "isbn is mandatory")
    private Long isbn;


    @NotBlank (message = "titre is mandatory")
    private String titre;


    private Long langueId;

    private Long editeurId;

    private Long genreId;

    private List<Long> auteursId = new ArrayList<>();



    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private Date dateDePublication;





}

package com.bibliotheque.livre.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class LivreForm {

    private static final long serialVersionUID = 1L;

    @NotNull (message = "isbn is madatory")
    private Long isbn;


    @NotBlank (message = "titre is mandatory")
    private String titre;


    private Long langueId;

    private Long editeurId;

    private Long genreId;

}

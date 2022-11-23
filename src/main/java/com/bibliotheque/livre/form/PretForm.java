package com.bibliotheque.livre.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.util.Date;

@Getter
@Setter
public class PretForm {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private Date dateDeDebut = new Date();

    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private Date dateDeFinSouhaitee;

    private Long exemplaireId;

    private Long userId;

    private Boolean renouvele = false;


}

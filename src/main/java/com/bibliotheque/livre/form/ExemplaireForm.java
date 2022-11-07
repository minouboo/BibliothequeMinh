package com.bibliotheque.livre.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ExemplaireForm {

    @NotNull (message = "code barre is mandatory")
    private String codeBarre;

    @NotNull(message = "livreID is mandatory")
    private Long livreId;


}

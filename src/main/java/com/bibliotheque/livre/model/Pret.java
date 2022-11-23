package com.bibliotheque.livre.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@Entity
@Table(name = "pret")

public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_debut")
    private Date dateDeDebut;

    @DateTimeFormat (pattern = "yyyy-MM-dd")
    @Column(name = "date_fin")
    private Date dateDeFin;

    @DateTimeFormat (pattern = "yyyy-MM-dd")
    @Column(name = "date_fin_souhaitee")
    private Date dateDeFinSouhaitee;

    @Column(columnDefinition = "boolean default false" ,name = "renouvele")
    private Boolean renouvele = false;

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user ;

    @ManyToOne
    @JoinColumn (name="exemplaire_id")
    private Exemplaire exemplaire ;




}

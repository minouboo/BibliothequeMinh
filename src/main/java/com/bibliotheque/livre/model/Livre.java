package com.bibliotheque.livre.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "livre")

public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Basic(optional = false)
    @Column(name = "titre")
    private String titre;

    @Basic
    @Column(name = "editeur_id")
    private String editeur_id;

    @Basic
    @Column(name = "date_de_publication")
    private Date date_de_publication;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "langue")
    private String langue;

    @Basic
    @Column(name = "auteur")
    private String auteur;

    @Basic
    @Column(name = "genre")
    private String genre;

}

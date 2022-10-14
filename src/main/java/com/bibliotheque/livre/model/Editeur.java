package com.bibliotheque.livre.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "editeur", indexes = {@Index(columnList = "nom")})

//{@Index(columnList = "nom"),{@Index(columnList = "prenom")}} pour indexer une recherche simple
//{@Index(columnList = "nom", "prenom)} pour indexer nom et prenom en meme temps

public class Editeur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column (unique = true)
    private String nom;

    @OneToMany (mappedBy = "editeur")
    private Set<Livre> livres;

}


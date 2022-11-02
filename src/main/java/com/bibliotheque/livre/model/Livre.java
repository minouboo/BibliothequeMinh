package com.bibliotheque.livre.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "livre", indexes = {@Index(columnList = "titre"), @Index(columnList = "isbn")})

public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(name = "isbn", unique = true)
    private Long isbn; //isbn = numero d'identification d'un livre que l'on va renseigner nous même

    @Basic(optional = false) //Pour rendre un attribut null ou pas
    @Column
    private String titre;

    @DateTimeFormat (pattern = "yyyy-MM-dd")
    @Column(name = "date_de_publication")
    private Date dateDePublication;

    @ManyToOne
    @JoinColumn (name="editeur_id")
    private Editeur editeur;

    @ManyToOne
    @JoinColumn (name="langue_id")
    private Langue langue;

    @OneToOne
    @JoinColumn (name = "description_id")
    private Description description;

    @ManyToMany
    @JoinTable(
            name = "livre_auteur",
            joinColumns = @JoinColumn (name = "auteur_id"),
            inverseJoinColumns = @JoinColumn (name = "livre_id"))
            private Set<Auteur> auteurs = new HashSet<>();

    /*@ManyToOne
    @JoinColumn (name="auteur_id")
    private Auteur auteur; */


    @ManyToOne
    @JoinColumn (name="genre_id")
    private Genre genre;

    @OneToMany (mappedBy = "livre")
    private List<Exemplaire> exemplaires = new ArrayList<>();




}




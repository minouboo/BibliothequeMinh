package com.bibliotheque.livre.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @NonNull
    private Long isbn; //isbn = numero d'identification d'un livre que l'on va renseigner nous même

    @Basic(optional = false) //Pour rendre un attribut null ou pas
    @Column
    private String titre;

    @Basic
    @Column(name = "date_de_publication")
    private Date dateDePublication;

    @ManyToOne
    @JoinColumn (name="editeur_nom")
    private Editeur editeur;

    @OneToMany(mappedBy = "langue")
    private List<Langue> langues = new ArrayList<>();

    @OneToOne
    @JoinColumn (name = "description_id")
    private Description description;

    @ManyToOne
    @JoinColumn (name="auteur_id")
    private Auteur auteur;

    @ManyToOne
    @JoinColumn (name="genre_nom")
    private Genre genre;



}




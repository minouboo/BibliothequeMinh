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
    private long id;

    @Basic(optional = false)
    @Column
    private String titre;

    @Basic
    @Column
    private String editeur_id;

    @Basic
    @Column
    private Date date_de_publication;

    @Basic
    @Column (length = 3000)
    private String description;

    @Basic
    @Column
    private String langue;

    @Basic
    @Column
    private String auteur;

    @Basic
    @Column
    private String genre;

    @Basic
    @Column
    private String isbn;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEditeur_id() {
        return editeur_id;
    }

    public void setEditeur_id(String editeur_id) {
        this.editeur_id = editeur_id;
    }

    public Date getDate_de_publication() {
        return date_de_publication;
    }

    public void setDate_de_publication(Date date_de_publication) {
        this.date_de_publication = date_de_publication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

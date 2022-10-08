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
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;

    @Basic
    @Column(name = "prenom")
    private String prenom;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "mdp")
    private String mdp;

    @Basic
    @Column(name = "sel")
    private String sel;

}

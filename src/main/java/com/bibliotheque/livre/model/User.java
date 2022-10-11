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
    private Long id;

    @Basic
    @Column
    private String nom;

    @Basic
    @Column
    private String prenom;

    @Basic
    @Column
    private String email;

    @Basic
    @Column
    private String mdp;

    @Basic
    @Column
    private String sel;

}

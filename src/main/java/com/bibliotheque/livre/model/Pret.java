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
@Table(name = "pret")

public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Basic
    @Column(name = "date_debut")
    private Date date_debut;

    @Basic
    @Column(name = "date_fin")
    private Date date_fin;

    @Column(columnDefinition = "boolean default true" ,name = "renouvele")
    private boolean renouvele;

}

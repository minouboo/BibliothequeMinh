package com.bibliotheque.livre.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

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
    private Long id;

    @Basic
    @Column
    private Date date_debut;

    @Basic
    @Column
    private Date date_fin;

    @Column(columnDefinition = "boolean default false" ,name = "renouvele")
    private Boolean renouvele = false;

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user ;

    @ManyToOne
    @JoinColumn (name="exemplaire_id")
    private Exemplaire exemplaire ;




}

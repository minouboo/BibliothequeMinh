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
    long id;

    @Basic
    @Column
    private Date date_debut;

    @Basic
    @Column
    private Date date_fin;

    @Column(columnDefinition = "boolean default true" ,name = "renouvele")
    private boolean renouvele;

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user ;

}

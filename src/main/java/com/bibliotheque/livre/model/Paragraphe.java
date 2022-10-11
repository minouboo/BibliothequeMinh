package com.bibliotheque.livre.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "paragraphe")

public class Paragraphe {

    @Id
    private Integer ordre;

    @Basic
    @Column
    private String texte;

    @ManyToOne
    @JoinColumn (name="description_id")
    private Description description;

}

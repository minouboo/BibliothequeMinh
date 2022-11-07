package com.bibliotheque.livre.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@Entity
@Table(name = "exemplaire")

public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String codeBarre;

    @ManyToOne
    @JoinColumn (name="livre_id")
    private Livre livre;
    

    @OneToMany (mappedBy = "exemplaire")
    private Set<Pret> prets;



}

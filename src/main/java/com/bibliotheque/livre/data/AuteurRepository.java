package com.bibliotheque.livre.data;

import com.bibliotheque.livre.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuteurRepository extends JpaRepository <Auteur, Long> {
}

package com.bibliotheque.livre.data;

import com.bibliotheque.livre.model.Editeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditeurRepository extends JpaRepository<Editeur, Long> {
}

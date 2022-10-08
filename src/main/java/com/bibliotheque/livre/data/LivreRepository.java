package com.bibliotheque.livre.data;

import com.bibliotheque.livre.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LivreRepository extends JpaRepository <Livre,Long>{

    @Query("SELECT l FROM Livre l WHERE lower(l.nom) like %:search% OR lower(l.auteur) like %:search% ")
    List<Livre> findLivresByFilters(@Param("search") String search);


}

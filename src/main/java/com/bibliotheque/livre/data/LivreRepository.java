package com.bibliotheque.livre.data;

import com.bibliotheque.livre.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LivreRepository extends JpaRepository <Livre,Long>{


/*
    @Query("SELECT l FROM Livre WHERE l.dateDePublication is null")
    List<Livre> findLivresBySF();

    */



    /*

    @Query("SELECT l FROM Livre l WHERE lower(l.titre) like %:search% OR lower(l.auteur) like %:search% ")
    List<Livre> findLivresByFilters(@Param("search") String search);

*/




}

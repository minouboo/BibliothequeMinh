package com.bibliotheque.livre.data;

import com.bibliotheque.livre.model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
	
	//nombre exemplaire dispo le nombre d'exemplaire d'un livre qui n'a pas de pret en cours
	//public long countBy(Long livreId,);
	//recuperer un exemplaire dispo
	

}

package com.bibliotheque.livre.data;

import com.bibliotheque.livre.model.Exemplaire;
import com.bibliotheque.livre.model.Livre;
import com.bibliotheque.livre.model.Pret;
import com.bibliotheque.livre.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Repository
public interface PretRepository extends JpaRepository<Pret, Long> {


	/*
	@Query ("select p from Pret p inner join p.user u where u.id = ?")
	List<Pret> getUserPret(Long userId);

	 */
}

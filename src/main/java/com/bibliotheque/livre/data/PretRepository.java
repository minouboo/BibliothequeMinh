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
	 * List<Pret> findReservationsByAttenteTrue();
	 * 
	 * @Query("SELECT r \n" + "FROM Reservation r \n" +
	 * "WHERE (:livre IS NULL OR r.livre = :livre) \n" +
	 * "AND (:utilisateur IS NULL OR r.utilisateur = :utilisateur )" + " \n" +
	 * "AND r.dateCreation <= :date" + " AND r.rendu = false ") List<Pret>
	 * findActualReservationsWithLivre(@Param("livre") Livre
	 * livre, @Param("utilisateur") User user, @Param("date") Date date);
	 * 
	 * @Query("SELECT r FROM Reservation r WHERE r.livre = :livre AND r.rendu=false AND r.attente = false"
	 * ) List<Pret> getReservationsOfaBookInProgress(@Param("livre") Livre livre);
	 * 
	 * @Query("SELECT r FROM Reservation r WHERE r.livre = :livre AND r.attente = true ORDER BY r.dateCreation"
	 * ) List<Pret> getReservationWaitingOfaBook(Livre livre);
	 * 
	 * Pret findFirstByLivreAndAttenteTrueOrderByDateCreationAsc(Livre livre);
	 * 
	 * List<Pret> findReservationsByAlertedTrue() ;
	 * 
	 * List<Pret> findReservationsByAttenteFalseAndRenduFalse();
	 */

	// recuperer un exemplaire d'un utilisateur

	public List<Pret> findByUserIdAndLivreId(Long userId,Long livreId);
}

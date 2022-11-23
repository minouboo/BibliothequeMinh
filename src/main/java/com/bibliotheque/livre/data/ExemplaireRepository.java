package com.bibliotheque.livre.data;

import com.bibliotheque.livre.model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {

    //nombre exemplaire dispo le nombre d'exemplaire d'un livre qui n'a pas de pret en cours
    //public long countBy(Long livreId,);


    //recuperer un exemplaire dispo d'un livre

    //select * from Exemplaire e inner join livre l on l.id = e.livre_id left join pret p on p.exemplaire_id = e.id where l.id = 13 and ( p.id is null or p.date_fin is not null)
    @Query("select e from Exemplaire e inner join e.livre l left join e.prets p where l.id = ?1 and ( p.user.id is null or p.dateDeFin is not null)")
    List<Exemplaire> getExemplaireDispoLivre(Long livreId);

}




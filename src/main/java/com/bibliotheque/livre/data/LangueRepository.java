package com.bibliotheque.livre.data;

import com.bibliotheque.livre.model.Langue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LangueRepository extends JpaRepository<Langue, Long> {
}

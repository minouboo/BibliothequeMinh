package com.bibliotheque.livre.data;

import com.bibliotheque.livre.model.Paragraphe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParagrapheRepository extends JpaRepository<Paragraphe, Long> {
}

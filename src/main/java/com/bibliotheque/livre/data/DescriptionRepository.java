package com.bibliotheque.livre.data;

import com.bibliotheque.livre.model.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends JpaRepository <Description, Long>{



}

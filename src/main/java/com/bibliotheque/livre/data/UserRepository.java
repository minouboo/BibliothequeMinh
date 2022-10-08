package com.bibliotheque.livre.data;

import com.bibliotheque.livre.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends JpaRepository <User, Long> {

    User findUserByMail(String mail);
}

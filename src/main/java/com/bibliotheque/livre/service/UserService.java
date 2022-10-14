package com.bibliotheque.livre.service;

import java.util.List;

import com.bibliotheque.livre.model.User;

public interface UserService {

    //pour une liste de user
    List<User> getAllUser();

    //pour enregistrer un user
    User saveUser (User user);

    //pour recuperer un user avec son id
    User getUserById (Long id);

    //pour modifier un user
    User updateUser(User user);


    //pour supprimer un user
    void deleteUserById (long id);

	User getCurrentUser();

}

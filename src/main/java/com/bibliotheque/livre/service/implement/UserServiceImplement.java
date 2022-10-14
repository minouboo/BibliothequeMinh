package com.bibliotheque.livre.service.implement;

import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bibliotheque.livre.data.UserRepository;
import com.bibliotheque.livre.model.User;
import com.bibliotheque.livre.service.UserService;


@Service
public class UserServiceImplement implements UserService {

    private UserRepository userRepository;

    public UserServiceImplement (UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    //methode pour avoir une liste de User
    @Override
    public List<User> getAllUser () {
        return userRepository.findAll();
    }

    // methode pour sauvegarder un livre
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public User getCurrentUser() {
    	
    	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
    		return this.userRepository.findByUsername(authentication.getName()).orElse(null);
    	}
    	
    	return null;
    }

}

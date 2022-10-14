package com.bibliotheque.livre.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotheque.livre.model.Exemplaire;
import com.bibliotheque.livre.model.Pret;
import com.bibliotheque.livre.model.User;
import com.bibliotheque.livre.service.PretService;
import com.bibliotheque.livre.service.UserService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

@Log
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@AllArgsConstructor
@RestController
@RequestMapping(value = "/pret") // This means URL's start with /demo (after Application path)

public class PretController {

	@Autowired
	private PretService pretService;
	
	@Autowired
	private UserService userService;
	

	public PretController(PretService pretService, UserService userService) {
		this.pretService = pretService;
		this.userService = userService;
	}

	@PostMapping("/emprunter")
	public String emprunter(@RequestParam("id") Long id) {
		
		User user = this.userService.getCurrentUser();
		if(user!=null) {
			Pret pret = new Pret();
			pret.setDate_debut(new java.util.Date());
			pret.setUser(user);
			pret.setExemplaire(null);
		}
		
		return null;

	}

	public String rendre(Exemplaire exemplaire) {
		Pret pre=new Pret();
		//pre.setDate_fin(new Date());
		
		return null;

	}

}

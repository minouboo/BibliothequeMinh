package com.bibliotheque.livre.controller;



import com.bibliotheque.livre.form.PretForm;
import com.bibliotheque.livre.service.ExemplaireService;
import com.bibliotheque.livre.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bibliotheque.livre.model.Exemplaire;
import com.bibliotheque.livre.model.Pret;
import com.bibliotheque.livre.model.User;
import com.bibliotheque.livre.service.PretService;
import com.bibliotheque.livre.service.UserService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;

@Log
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@RequestMapping(value = "/pret")

public class PretController {

	private PretService pretService;
	private UserService userService;
	private LivreService livreService;

	private ExemplaireService exemplaireService;

	@Autowired
	public PretController(PretService pretService, UserService userService, LivreService livreService, ExemplaireService exemplaireService) {
		this.pretService = pretService;
		this.userService = userService;
		this.livreService = livreService;
		this.exemplaireService = exemplaireService;
	}


	// Pret de livre
	@GetMapping(value = "/info/{id}")
	public String newPret (@PathVariable Long id, Model model){

		//Afficher les données du livre
		model.addAttribute("livre", livreService.getLivreById(id));

		//Entrer les données du pret
		PretForm pret = new PretForm();
		int noOfDays = 14; //i.e two weeks
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		Date date = calendar.getTime();
		pret.setDateDeFinSouhaitee(date);
		model.addAttribute("exemplaires", exemplaireService.getExemplaireDispoLivre(id));
		model.addAttribute("pret", pret);
		return "userpret";
	}

	@PostMapping (value = "/newpret")
	public String addPret (@Valid @ModelAttribute ("pret") PretForm pret ){
		Pret p = new Pret();
		p.setDateDeDebut(pret.getDateDeDebut());
		p.setDateDeFinSouhaitee(pret.getDateDeFinSouhaitee());
		Exemplaire exemplaire = exemplaireService.findExemplaireById(pret.getExemplaireId());
		p.setExemplaire(exemplaire);
        User user = userService.getCurrentUser();
		p.setUser(user);
		pretService.savePret(p);
		return "redirect:/compte/liste";
	}


	@PostMapping("/emprunter")
	public String emprunter(@RequestParam("id") Long id) {

		User user = this.userService.getCurrentUser();
		if(user!=null) {
			Pret pret = new Pret();
			pret.setDateDeDebut(new java.util.Date());
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


	//Supprimer un pret
	@GetMapping(value = "/rendu/{id}")
	public String rendreLePret(@PathVariable Long id, Pret pret){
		Pret pretrendu = pretService.getPretById(id);
		pretrendu.setUser(null);
		pretrendu.setDateDeFinSouhaitee(null);
		pretrendu.setDateDeDebut(null);
		pretrendu.setDateDeFin(null);
		pretService.updatePret(pretrendu);
		return "redirect:/compte/mesprets";
	}





}

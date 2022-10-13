package com.bibliotheque.livre.auth;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hello")
public class AccueilConnectedController {

    @GetMapping
    public String getHello(Model model){
        model.addAttribute("username", getAuthenticatedUsername());
        return "accueilconnecte";
    }

    private String getAuthenticatedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            return authentication.getName();

        }
        return null;
    }

    @GetMapping
    public String getHello(Model model, Authentication authentication){
        model.addAttribute("username", authentication.getName());
        return "accueilconnecte";
    }
}

package com.bibliotheque.livre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

    @GetMapping({"","/","/home"})
    public String getHome(){

        return "userpret";
    }
}
package br.com.example.exerciciossb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeiroController {

//    @RequestMapping(path = "/ola")
//    @GetMapping(path = "/ola")
    @GetMapping(path = {"/ola", "saudacao"})
    public String ola(){
        return "Olá Spring Boot!";
    }

}

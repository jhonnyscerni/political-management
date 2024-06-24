package br.com.projeto.politicalmanagement.api.controllers;

import br.com.projeto.politicalmanagement.service.PersonPhysicalService;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/dashboard/auth")
public class DashboardController {

    @Autowired
    private PersonPhysicalService personPhysicalService;


    @GetMapping("/countPersonPhysical")
    public Long countUserPerson(@RequestParam UUID id){
        return personPhysicalService.countPersonPhysical(id);
    }

    @GetMapping("/countPersonPhysicalVoteIsConquistado")
    public Long countUserByIdAndPersonVoteIsConquistado(@RequestParam UUID id){
        return personPhysicalService.countPersonPhysicalVoteIsConquistado(id);
    }

    @GetMapping("/countPersonPhysicalVoteIsAConquistar")
    public Long countUserByIdAndPersonVoteIsAConquistar(@RequestParam UUID id){
        return personPhysicalService.countPersonPhysicalVoteIsAConquistar(id);
    }

    @GetMapping("/countPersonPhysicalVoteIsPerdido")
    public Long countUserByIdAndPersonVoteIsPerdido(@RequestParam UUID id){
        return personPhysicalService.countPersonPhysicalVoteIsPerdido(id);
    }

}
